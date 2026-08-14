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
import com.sm64tracker.model.Course;

public class CourseRepository {

    public List<Course> findAll() {
        String sql = "SELECT id, name, abbreviation, course_number, course_type FROM courses ORDER BY course_number ASC, name ASC";
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(mapCourse(resultSet));
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to load courses.", exception);
        }

        return courses;
    }

    public Optional<Course> findById(long courseId) {
        String sql = "SELECT id, name, abbreviation, course_number, course_type FROM courses WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapCourse(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to fetch course by id.", exception);
        }

        return Optional.empty();
    }

    public List<Course> findAllByType(String courseType) {
        String sql = "SELECT id, name, abbreviation, course_number, course_type FROM courses WHERE course_type = ? ORDER BY course_number ASC";
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, courseType);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    courses.add(mapCourse(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to load courses by type.", exception);
        }

        return courses;
    }

    public long insertIfNotExists(String name, String abbreviation, int courseNumber, String courseType) {
        String selectSql = "SELECT id FROM courses WHERE name = ? OR abbreviation = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {

            selectStatement.setString(1, name);
            selectStatement.setString(2, abbreviation);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }
            }

            String insertSql = "INSERT INTO courses (name, abbreviation, course_number, course_type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, name);
                insertStatement.setString(2, abbreviation);
                insertStatement.setInt(3, courseNumber);
                insertStatement.setString(4, courseType);
                insertStatement.executeUpdate();
            }

            try (PreparedStatement fetchStatement = connection.prepareStatement(selectSql)) {
                fetchStatement.setString(1, name);
                fetchStatement.setString(2, abbreviation);
                try (ResultSet resultSet = fetchStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("id");
                    }
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to insert course.", exception);
        }

        throw new IllegalStateException("Course creation failed for " + name);
    }

    public long countStarsWithPb(long courseId) {
        String sql = """
            SELECT COUNT(DISTINCT pb.star_id) AS pb_count
            FROM stars s
            LEFT JOIN personal_bests pb ON pb.star_id = s.id
            WHERE s.course_id = ?
            """;

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("pb_count");
                }
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to count PBs for course.", exception);
        }

        return 0;
    }

    public List<Course> findWithPbCount() {
        String sql = """
            SELECT c.id, c.name, c.abbreviation, c.course_number, c.course_type,
                   COUNT(DISTINCT pb.star_id) AS pb_count
            FROM courses c
            LEFT JOIN stars s ON s.course_id = c.id
            LEFT JOIN personal_bests pb ON pb.star_id = s.id
            GROUP BY c.id, c.name, c.abbreviation, c.course_number, c.course_type
            ORDER BY c.course_number ASC, c.name ASC
            """;

        List<Course> courses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getDatabaseUrl());
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(mapCourse(resultSet));
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to list courses with PB counts.", exception);
        }

        return courses;
    }

    private Course mapCourse(ResultSet resultSet) throws SQLException {
        return new Course(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("abbreviation"),
                resultSet.getInt("course_number"),
                resultSet.getString("course_type")
        );
    }
}
