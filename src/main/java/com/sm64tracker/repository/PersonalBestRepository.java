package com.sm64tracker.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sm64tracker.database.DatabaseConfig;
import com.sm64tracker.model.PersonalBest;

public class PersonalBestRepository {

    public List<PersonalBest> findByStarId(long starId) {
        String sql = "SELECT id, star_id, time_in_ms, achieved_at FROM personal_bests WHERE star_id = ? ORDER BY achieved_at ASC";
        List<PersonalBest> personalBests = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, starId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    personalBests.add(mapPersonalBest(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to load PB history for star.", exception);
        }

        return personalBests;
    }

    public Optional<Long> findCurrentPbTimeMs(long starId) {
        String sql = "SELECT time_in_ms FROM personal_bests WHERE star_id = ? ORDER BY time_in_ms ASC, achieved_at DESC LIMIT 1";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, starId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSet.getLong("time_in_ms"));
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to fetch current PB for star.", exception);
        }

        return Optional.empty();
    }

    public void insert(long starId, long timeInMs, LocalDateTime achievedAt) {
        String sql = "INSERT INTO personal_bests (star_id, time_in_ms, achieved_at) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, starId);
            statement.setLong(2, timeInMs);
            statement.setTimestamp(3, Timestamp.valueOf(achievedAt));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to insert new PB.", exception);
        }
    }

    private PersonalBest mapPersonalBest(ResultSet resultSet) throws SQLException {
        return new PersonalBest(
                resultSet.getLong("id"),
                resultSet.getLong("star_id"),
                resultSet.getLong("time_in_ms"),
                resultSet.getTimestamp("achieved_at").toLocalDateTime()
        );
    }
}
