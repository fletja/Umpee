package com.sm64tracker.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sm64tracker.database.DatabaseConfig;
import com.sm64tracker.model.Star;

public class StarRepository {

    public List<Star> findByCourseId(long courseId) {
        String sql = "SELECT id, course_id, name, star_number, coin_star FROM stars WHERE course_id = ? ORDER BY star_number ASC";
        List<Star> stars = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    stars.add(mapStar(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to load stars for course.", exception);
        }

        return stars;
    }

    public Optional<Star> findById(long starId) {
        String sql = "SELECT id, course_id, name, star_number, coin_star FROM stars WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, starId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapStar(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to fetch star by id.", exception);
        }

        return Optional.empty();
    }

    public long insertIfNotExists(long courseId, String name, int starNumber, boolean coinStar) {
        String selectSql = "SELECT id FROM stars WHERE course_id = ? AND name = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {

            selectStatement.setLong(1, courseId);
            selectStatement.setString(2, name);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }
            }

            String insertSql = "INSERT INTO stars (course_id, name, star_number, coin_star) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setLong(1, courseId);
                insertStatement.setString(2, name);
                insertStatement.setInt(3, starNumber);
                insertStatement.setInt(4, coinStar ? 1 : 0);
                insertStatement.executeUpdate();
            }

            try (PreparedStatement fetchStatement = connection.prepareStatement(selectSql)) {
                fetchStatement.setLong(1, courseId);
                fetchStatement.setString(2, name);
                try (ResultSet resultSet = fetchStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("id");
                    }
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to insert star.", exception);
        }

        throw new IllegalStateException("Star creation failed for " + name);
    }

    private Star mapStar(ResultSet resultSet) throws SQLException {
        return new Star(
                resultSet.getLong("id"),
                resultSet.getLong("course_id"),
                resultSet.getString("name"),
                resultSet.getInt("star_number"),
                resultSet.getInt("coin_star") == 1
        );
    }
}
