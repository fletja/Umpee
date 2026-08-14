package com.sm64tracker.database;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DatabaseConfig {
    private static Path databasePathOverride;

    private DatabaseConfig() {
    }

    public static void setDatabasePathOverride(Path path) {
        databasePathOverride = path;
    }

    public static String getDatabaseUrl() {
        Path path = databasePathOverride != null ? databasePathOverride : defaultDatabasePath();
        try {
            Files.createDirectories(path.getParent());
        } catch (Exception ignored) {
            // The parent path can be absent on some platforms; this is a best effort.
        }
        return "jdbc:sqlite:" + path.toAbsolutePath();
    }

    private static Path defaultDatabasePath() {
        String os = System.getProperty("os.name").toLowerCase();
        Path baseDir;

        if (os.contains("win")) {
            baseDir = Paths.get(System.getProperty("user.home"), "AppData", "Roaming", "SM64 Star PB Tracker");
        } else if (os.contains("mac")) {
            baseDir = Paths.get(System.getProperty("user.home"), "Library", "Application Support", "SM64 Star PB Tracker");
        } else {
            baseDir = Paths.get(System.getProperty("user.home"), ".sm64-star-pb-tracker");
        }

        return baseDir.resolve("sm64-pb-tracker.db");
    }
}
