# Campus Course Registration System

A Java Console Application for managing university courses, students, and their registration.
The project uses **PostgreSQL** for data storage and follows the **Repository Pattern** with a **Service Layer** for business logic.

## Features

* **Students Management**:
    * View all students
    * Add new student
    * Search student by name
    * Delete student
* **Courses Management**:
    * View all courses
    * Add new course
    * Search course by title
    * Delete course (with protection against deleting courses with active students)
* **Registration**:
    * Register student to a course (with capacity check)
    * Drop course (Unsubscribe)
    * View all registrations

## Technologies

* Java 17+
* PostgreSQL (Supabase)
* JDBC (Java Database Connectivity)

## Setup & Installation

1.  Clone the repository.
2.  **Database Configuration**:
    > ⚠️ **Note:** The `config.properties` file containing database credentials is not included in this repository for security reasons.
    >
    > **Please contact the authors to obtain access credentials or the configuration file.**
3.  Run `Main.java`.

## Author

**Group IT2512**
* Bissengali Sanzhar
* Almukhanov Yernur