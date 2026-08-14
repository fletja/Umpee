package com.sm64tracker.model;

import java.time.LocalDateTime;

public class PersonalBest {
    private final long id;
    private final long starId;
    private final long timeInMs;
    private final LocalDateTime achievedAt;

    public PersonalBest(long id, long starId, long timeInMs, LocalDateTime achievedAt) {
        this.id = id;
        this.starId = starId;
        this.timeInMs = timeInMs;
        this.achievedAt = achievedAt;
    }

    public long getId() {
        return id;
    }

    public long getStarId() {
        return starId;
    }

    public long getTimeInMs() {
        return timeInMs;
    }

    public LocalDateTime getAchievedAt() {
        return achievedAt;
    }
}
