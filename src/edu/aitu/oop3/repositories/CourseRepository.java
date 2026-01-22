package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.models.Course;
import edu.aitu.oop3.repositories.interfaces.ICourseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements ICourseRepository {
    private final IDB db;

    public CourseRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, instructor, credits FROM courses";
        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("instructor"),
                        rs.getInt("credits")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении курсов: " + e.getMessage());
        }
        return courses;
    }

    @Override
    public Course getById(int id) {
        String sql = "SELECT id, title, instructor, credits FROM courses WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("instructor"),
                            rs.getInt("credits")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT INTO courses(title, instructor, credits, capacity) VALUES(?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, course.getTitle());
            st.setString(2, course.getInstructor());
            st.setInt(3, course.getCredits());
            st.setInt(4, 50);
            st.executeUpdate();
            System.out.println("Course added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Course deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting course: " + e.getMessage());
        }
    }
}