package edu.aitu.oop3.services;

import edu.aitu.oop3.models.Course;
import edu.aitu.oop3.repositories.interfaces.ICourseRepository;
import edu.aitu.oop3.repositories.interfaces.IRegistrationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private final ICourseRepository courseRepo;
    private final IRegistrationRepository regRepo;

    public CourseService(ICourseRepository courseRepo, IRegistrationRepository regRepo) {
        this.courseRepo = courseRepo;
        this.regRepo = regRepo;
    }

    public List<Course> getFilteredCourses(int minCredits) {
        return courseRepo.getAll().stream()
                .filter(c -> c.getCredits() >= minCredits)
                .collect(Collectors.toList());
    }

    public void registerStudent(int studentId, int courseId) throws Exception {
        int currentCount = regRepo.getStudentCountByCourseId(courseId);
        int limit = 50;

        if (currentCount >= limit) {
            throw new Exception("Group capacity exceeded! Cannot register more students.");
        }

        boolean success = regRepo.registerStudent(studentId, courseId);
        if (success) {
            System.out.println("Successfully registered!");
        } else {
            throw new Exception("Student is likely already registered for this course.");
        }
    }

    public void deleteCourse(int courseId) throws Exception {
        int activeStudents = regRepo.getStudentCountByCourseId(courseId);

        if (activeStudents > 0) {
            throw new Exception("Cannot delete course! There are " + activeStudents + " active enrollments.");
        }

        courseRepo.delete(courseId);
    }

    public void dropCourse(int studentId, int courseId) {
        regRepo.deleteRegistration(studentId, courseId);
        System.out.println("Course dropped successfully.");
    }
}