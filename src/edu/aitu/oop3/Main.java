package edu.aitu.oop3;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.models.Course;
import edu.aitu.oop3.models.Student;
import edu.aitu.oop3.repositories.CourseRepository;
import edu.aitu.oop3.repositories.StudentRepository;
import edu.aitu.oop3.repositories.interfaces.ICourseRepository;
import edu.aitu.oop3.repositories.interfaces.IStudentRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = new DatabaseConnection();
        ICourseRepository courseRepo = new CourseRepository(db);
        IStudentRepository studentRepo = new StudentRepository(db);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- University System Management ---");
            System.out.println("1. View all courses");
            System.out.println("2. Add new course");
            System.out.println("3. Search course by name");
            System.out.println("4. Delete course");
            System.out.println("-------------------------");
            System.out.println("5. View all students");
            System.out.println("6. Add new student");
            System.out.println("7. Search student by name");
            System.out.println("8. Delete student");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    courseRepo.getAll().forEach(System.out::println);
                } else if (choice == 2) {
                    System.out.print("Title: "); String t = scanner.nextLine();
                    System.out.print("Instructor: "); String i = scanner.nextLine();
                    System.out.print("Credits: "); int c = Integer.parseInt(scanner.nextLine());
                    courseRepo.create(new Course(0, t, i, c));
                } else if (choice == 3) {
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    courseRepo.findByName(name).forEach(System.out::println);
                } else if (choice == 4) {
                    System.out.print("Enter Course ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    courseRepo.delete(id);
                } else if (choice == 5) {
                    studentRepo.getAll().forEach(System.out::println);
                } else if (choice == 6) {
                    System.out.print("Name: "); String n = scanner.nextLine();
                    System.out.print("Email: "); String e = scanner.nextLine();
                    studentRepo.create(new Student(0, n, e));
                } else if (choice == 7) {
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    studentRepo.findByName(name).forEach(System.out::println);
                } else if (choice == 8) {
                    System.out.print("Enter Student ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    studentRepo.delete(id);
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}