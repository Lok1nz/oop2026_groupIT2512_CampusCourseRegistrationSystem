package edu.aitu.oop3.models;

public class Course {
    private int id;
    private String title;
    private String instructor;
    private int credits;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getInstructor() { return instructor; }
    public int getCredits() { return credits; }

    public Course(int id, String title, String instructor, int credits) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.credits = credits;
    }

    public static class Builder {
        private int id = 0;
        private String title;
        private String instructor;
        private int credits;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setInstructor(String instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder setCredits(int credits) {
            this.credits = credits;
            return this;
        }

        public Course build() {
            return new Course(id, title, instructor, credits);
        }
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", title='" + title + "', instructor='" + instructor + "', credits=" + credits + "}";
    }
}