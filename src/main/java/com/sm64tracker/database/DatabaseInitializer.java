package com.sm64tracker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseInitializer {
    private DatabaseInitializer() {
    }

    public static void initialize() {
        String url = DatabaseConfig.getDatabaseUrl();

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS courses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL UNIQUE,
                    abbreviation TEXT NOT NULL UNIQUE,
                    course_number INTEGER NOT NULL,
                    course_type TEXT NOT NULL DEFAULT 'MAIN'
                )
                """);

            // Migrate existing databases that predate the course_type column
            try {
                statement.executeUpdate("ALTER TABLE courses ADD COLUMN course_type TEXT NOT NULL DEFAULT 'MAIN'");
            } catch (SQLException ignored) {
            }

            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS stars (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    course_id INTEGER NOT NULL,
                    name TEXT NOT NULL,
                    star_number INTEGER NOT NULL,
                    coin_star INTEGER NOT NULL DEFAULT 0,
                    FOREIGN KEY (course_id) REFERENCES courses(id),
                    UNIQUE(course_id, name)
                )
                """);

            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS personal_bests (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    star_id INTEGER NOT NULL,
                    time_in_ms INTEGER NOT NULL,
                    achieved_at TEXT NOT NULL,
                    FOREIGN KEY (star_id) REFERENCES stars(id)
                )
                """);

            statement.executeUpdate("CREATE INDEX IF NOT EXISTS idx_personal_bests_star_id ON personal_bests(star_id)");
            statement.executeUpdate("CREATE INDEX IF NOT EXISTS idx_personal_bests_time ON personal_bests(time_in_ms)");
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to initialize the database.", exception);
        }
    }
}
