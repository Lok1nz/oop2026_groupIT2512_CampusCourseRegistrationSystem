package edu.aitu.oop3;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.models.Course;
import edu.aitu.oop3.repositories.CourseRepository;
import edu.aitu.oop3.repositories.interfaces.ICourseRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = new DatabaseConnection();
        ICourseRepository repo = new CourseRepository(db);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- University System ---");
            System.out.println("1. View all courses");
            System.out.println("2. Find course by ID");
            System.out.println("3. Add new course");
            System.out.println("0. Exit");
            System.out.print("Select: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    List<Course> allCourses = repo.getAll();
                    if (allCourses.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        allCourses.forEach(System.out::println);
                    }
                } else if (choice == 2) {
                    System.out.print("Enter ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Course course = repo.getById(id);
                    System.out.println(course != null ? "Found: " + course : "Course not found!");
                } else if (choice == 3) {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter instructor: ");
                    String instructor = scanner.nextLine();
                    System.out.print("Enter credits: ");
                    int credits = Integer.parseInt(scanner.nextLine());
                    repo.create(new Course(0, title, instructor, credits));
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Unknown option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}