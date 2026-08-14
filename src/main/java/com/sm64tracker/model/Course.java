package com.sm64tracker.model;

public class Course {
    private final long id;
    private final String name;
    private final String abbreviation;
    private final int courseNumber;
    private final String courseType;

    public Course(long id, String name, String abbreviation, int courseNumber, String courseType) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.courseNumber = courseNumber;
        this.courseType = courseType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getCourseType() {
        return courseType;
    }
}
