package edu.aitu.oop3.factories;

import edu.aitu.oop3.models.*;

public class EntityFactory {

    public static Student createStudent(String name, String email) {
        return new Student(0, name, email);
    }

    public static Course createCourse(String type, String title, String instructor, int credits) {
        if (type == null) {
            return new LectureCourse(0, title, instructor, credits);
        }

        switch (type.toUpperCase()) {
            case "LAB":
                return new LabCourse(0, title, instructor, credits);
            case "ONLINE":
                return new OnlineCourse(0, title, instructor, credits);
            case "LECTURE":
            default:
                return new LectureCourse(0, title, instructor, credits);
        }
    }
}