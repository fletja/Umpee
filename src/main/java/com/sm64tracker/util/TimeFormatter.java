package com.sm64tracker.util;

import java.util.Locale;

public final class TimeFormatter {
    private TimeFormatter() {
    }

    public static long parseToMilliseconds(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Time value cannot be blank.");
        }

        String normalized = value.trim();
        if (normalized.matches("\\d+\\.?\\d+")) {
            return Math.round(Double.parseDouble(normalized) * 1000.0);
        }

        if (normalized.matches("\\d+:\\d{1,2}\\.\\d+")) {
            String[] parts = normalized.split(":");
            long minutes = Long.parseLong(parts[0]);
            double seconds = Double.parseDouble(parts[1]);
            return Math.round((minutes * 60_000.0) + (seconds * 1000.0));
        }

        if (normalized.matches("\\d+:\\d{1,2}\\.\\d+")) {
            String[] parts = normalized.split(":");
            long minutes = Long.parseLong(parts[0]);
            double seconds = Double.parseDouble(parts[1]);
            return Math.round((minutes * 60_000.0) + (seconds * 1000.0));
        }

        if (normalized.matches("\\d{1,2}:\\d{2}\\.\\d{2}")) {
            String[] parts = normalized.split(":");
            long minutes = Long.parseLong(parts[0]);
            double seconds = Double.parseDouble(parts[1]);
            return Math.round((minutes * 60_000.0) + (seconds * 1000.0));
        }

        if (normalized.matches("\\d+\\.\\d+")) {
            return Math.round(Double.parseDouble(normalized) * 1000.0);
        }

        if (normalized.matches("\\d{1,2}\\.\\d{2}")) {
            return Math.round(Double.parseDouble(normalized) * 1000.0);
        }

        throw new IllegalArgumentException("Unsupported time format: " + value);
    }

    public static String formatFromMilliseconds(long milliseconds) {
        long totalMilliseconds = Math.max(0L, milliseconds);
        long minutes = totalMilliseconds / 60_000L;
        long remainingMs = totalMilliseconds % 60_000L;
        long seconds = remainingMs / 1000L;
        long centiseconds = (remainingMs % 1000L) / 10L;

        if (minutes > 0) {
            return String.format(Locale.US, "%d:%02d.%02d", minutes, seconds, centiseconds);
        }

        return String.format(Locale.US, "%d.%02d", seconds, centiseconds);
    }

    public static String formatDetailedFromMilliseconds(long milliseconds) {
        long totalMilliseconds = Math.max(0L, milliseconds);
        long minutes = totalMilliseconds / 60_000L;
        long remainingMs = totalMilliseconds % 60_000L;
        long seconds = remainingMs / 1000L;
        long centiseconds = (remainingMs % 1000L) / 10L;

        if (minutes > 0) {
            return String.format(Locale.US, "%d:%02d.%02d", minutes, seconds, centiseconds);
        }

        return String.format(Locale.US, "%d.%02d", seconds, centiseconds);
    }
}
