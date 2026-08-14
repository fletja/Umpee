package com.sm64tracker.model;

public class Course {
    private final long id;
    private final String name;
    private final String abbreviation;
    private final int courseNumber;

    public Course(long id, String name, String abbreviation, int courseNumber) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.courseNumber = courseNumber;
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
}
