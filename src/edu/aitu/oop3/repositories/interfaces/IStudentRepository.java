package edu.aitu.oop3.repositories.interfaces;

import edu.aitu.oop3.models.Student;
import java.util.List;

public interface IStudentRepository {
    List<Student> getAll();
    void create(Student student);
    void delete(int id);
    List<Student> findByName(String name);
}