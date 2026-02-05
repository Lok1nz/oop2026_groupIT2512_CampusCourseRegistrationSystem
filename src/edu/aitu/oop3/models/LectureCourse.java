package edu.aitu.oop3.models;

public class LectureCourse extends Course {
    public LectureCourse(int id, String title, String instructor, int credits) {
        super(id, title, instructor, credits);
    }

    @Override
    public String toString() {
        return super.toString() + " [Type: LECTURE]";
    }
}