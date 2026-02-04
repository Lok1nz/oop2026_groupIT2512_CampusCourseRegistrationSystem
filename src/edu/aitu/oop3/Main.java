package edu.aitu.oop3;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.factories.EntityFactory;
import edu.aitu.oop3.models.Course;
import edu.aitu.oop3.models.Student;
import edu.aitu.oop3.repositories.CourseRepository;
import edu.aitu.oop3.repositories.RegistrationRepository;
import edu.aitu.oop3.repositories.StudentRepository;
import edu.aitu.oop3.repositories.interfaces.ICourseRepository;
import edu.aitu.oop3.repositories.interfaces.IRegistrationRepository;
import edu.aitu.oop3.repositories.interfaces.IStudentRepository;
import edu.aitu.oop3.services.CourseService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = DatabaseConnection.getInstance();

        ICourseRepository courseRepo = new CourseRepository(db);
        IStudentRepository studentRepo = new StudentRepository(db);
        IRegistrationRepository regRepo = new RegistrationRepository(db);

        CourseService courseService = new CourseService(courseRepo, regRepo);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Campus Course Registration System ---");
            System.out.println("1. View all courses (Sorted by credits)");
            System.out.println("2. Add new course");
            System.out.println("3. Search course by name");
            System.out.println("4. Delete course");
            System.out.println("-------------------------");
            System.out.println("5. View all students");
            System.out.println("6. Add new student");
            System.out.println("7. Search student by name");
            System.out.println("8. Delete student");
            System.out.println("-------------------------");
            System.out.println("9. Register student to course");
            System.out.println("10. Drop course");
            System.out.println("11. View registrations");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    List<Course> courses = courseRepo.getAll();
                    courses.stream()
                            .sorted((c1, c2) -> Integer.compare(c2.getCredits(), c1.getCredits()))
                            .forEach(System.out::println);
                } else if (choice == 2) {
                    System.out.print("Title: "); String t = scanner.nextLine();
                    System.out.print("Instructor: "); String i = scanner.nextLine();
                    System.out.print("Credits: "); int c = Integer.parseInt(scanner.nextLine());
                    Course course = EntityFactory.createCourse(t, i, c);
                    courseRepo.create(course);
                } else if (choice == 3) {
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    courseRepo.findByName(name).forEach(System.out::println);
                } else if (choice == 4) {
                    System.out.print("Enter Course ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    courseService.deleteCourse(id);
                } else if (choice == 5) {
                    studentRepo.getAll().forEach(System.out::println);
                } else if (choice == 6) {
                    System.out.print("Name: "); String n = scanner.nextLine();
                    System.out.print("Email: "); String e = scanner.nextLine();
                    Student student = EntityFactory.createStudent(n, e);
                    studentRepo.create(student);
                } else if (choice == 7) {
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    studentRepo.findByName(name).forEach(System.out::println);
                } else if (choice == 8) {
                    System.out.print("Enter Student ID to delete: ");
                    studentRepo.delete(Integer.parseInt(scanner.nextLine()));
                } else if (choice == 9) {
                    System.out.print("Enter Student ID: ");
                    int sid = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Course ID: ");
                    int cid = Integer.parseInt(scanner.nextLine());
                    courseService.registerStudent(sid, cid);
                } else if (choice == 10) {
                    System.out.print("Enter Student ID: ");
                    int sid = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Course ID to drop: ");
                    int cid = Integer.parseInt(scanner.nextLine());
                    courseService.dropCourse(sid, cid);
                } else if (choice == 11) {
                    regRepo.getAll().forEach(System.out::println);
                } else if (choice == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}