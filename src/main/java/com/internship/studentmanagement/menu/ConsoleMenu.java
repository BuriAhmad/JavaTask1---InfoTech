package com.internship.studentmanagement.menu;

import com.internship.studentmanagement.exception.DuplicateEmailException;
import com.internship.studentmanagement.exception.StudentNotFoundException;
import com.internship.studentmanagement.exception.ValidationException;
import com.internship.studentmanagement.model.Student;
import com.internship.studentmanagement.service.StudentService;
import com.internship.studentmanagement.service.StudentServiceImpl;
import com.internship.studentmanagement.util.DateUtil;
import com.internship.studentmanagement.util.InputUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final StudentService studentService = new StudentServiceImpl();
    private final Scanner scanner = new Scanner(System.in);
    private final InputUtil inputUtil = new InputUtil(scanner);

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = inputUtil.readLine("Enter your choice: ");

            try {
                switch (choice) {
                    case "1" -> addStudentMenu();
                    case "2" -> viewAllStudentsMenu();
                    case "3" -> searchStudentByIdMenu();
                    case "4" -> searchStudentByNameMenu();
                    case "5" -> updateStudentMenu();
                    case "6" -> deleteStudentMenu();
                    case "7" -> {
                        System.out.println("Exiting application. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (ValidationException | DuplicateEmailException | StudentNotFoundException exception) {
                System.out.println("Error: " + exception.getMessage());
            } catch (Exception exception) {
                System.out.println("Unexpected error occurred. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("==================================");
        System.out.println("Student Management System");
        System.out.println("=========================");
        System.out.println();
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Search Student by Name");
        System.out.println("5. Update Student");
        System.out.println("6. Delete Student");
        System.out.println("7. Exit Application");
        System.out.println("===================================");
    }

    private void addStudentMenu() {
        System.out.println("----- Add Student -----");
        Student student = readStudentDetails();
        studentService.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void viewAllStudentsMenu() {
        System.out.println("----- All Students -----");
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            printStudent(student);
        }
    }

    private void searchStudentByIdMenu() {
        System.out.println("----- Search Student by ID -----");
        int id = inputUtil.readInt("Enter student ID: ");
        Student student = studentService.searchStudentById(id);
        printStudent(student);
    }

    private void searchStudentByNameMenu() {
        System.out.println("----- Search Student by Name -----");
        String name = inputUtil.readLine("Enter student name or part of name: ");
        List<Student> matchedStudents = studentService.searchStudentByName(name);

        if (matchedStudents.isEmpty()) {
            System.out.println("No matching students found.");
            return;
        }

        for (Student student : matchedStudents) {
            printStudent(student);
        }
    }

    private void updateStudentMenu() {
        System.out.println("----- Update Student -----");
        int id = inputUtil.readInt("Enter student ID to update: ");
        Student existingStudent = studentService.searchStudentById(id);
        System.out.println("Current student details:");
        printStudent(existingStudent);
        System.out.println("Enter new student details:");
        Student updatedStudent = readStudentDetails();
        studentService.updateStudent(id, updatedStudent);
        System.out.println("Student updated successfully.");
    }

    private void deleteStudentMenu() {
        System.out.println("----- Delete Student -----");
        int id = inputUtil.readInt("Enter student ID to delete: ");
        studentService.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }

    private Student readStudentDetails() {
        Student student = new Student();
        student.setName(inputUtil.readLine("Enter name: "));
        student.setEmail(inputUtil.readLine("Enter email: "));
        student.setDateOfBirth(inputUtil.readDate("Enter date of birth yyyy-mm-dd: "));
        student.setMobileNumber(inputUtil.readLine("Enter mobile number: "));
        return student;
    }

    private void printStudent(Student student) {
        System.out.println("-----------------------------------");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Date of Birth: " + student.getDateOfBirth());
        System.out.println("Age: " + DateUtil.calculateAge(student.getDateOfBirth()));
        System.out.println("Mobile Number: " + student.getMobileNumber());
        System.out.println("-----------------------------------");
    }
}
