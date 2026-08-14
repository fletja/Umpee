package com.sm64tracker.model;

public class Star {
    private final long id;
    private final long courseId;
    private final String name;
    private final int starNumber;
    private final boolean coinStar;

    public Star(long id, long courseId, String name, int starNumber, boolean coinStar) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.starNumber = starNumber;
        this.coinStar = coinStar;
    }

    public long getId() {
        return id;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public int getStarNumber() {
        return starNumber;
    }

    public boolean isCoinStar() {
        return coinStar;
    }
}
