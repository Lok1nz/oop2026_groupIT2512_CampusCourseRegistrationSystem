package edu.aitu.oop3.repositories.interfaces;

import edu.aitu.oop3.models.Course;
import java.util.List;

public interface ICourseRepository {
    List<Course> getAll();
    Course getById(int id);
    void create(Course course);
    void delete(int id);
    List<Course> findByName(String name);
}