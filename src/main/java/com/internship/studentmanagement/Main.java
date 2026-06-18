package com.internship.studentmanagement;

import com.internship.studentmanagement.model.Student;
import com.internship.studentmanagement.service.StudentService;
import com.internship.studentmanagement.service.StudentServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class Main {

    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();

        testAddStudents(studentService);
        testViewAllStudents(studentService);
        testSearchStudentById(studentService);
        testSearchStudentByName(studentService);
        testUpdateStudent(studentService);
        testDeleteStudent(studentService);
        testSearchDeletedStudent(studentService);

        printTestSummary();
    }

    private static void testAddStudents(StudentService studentService) {
        Student student1 = createStudent(
                "Ali Khan",
                "ali@gmail.com",
                LocalDate.of(2000, 5, 10),
                "+923126665505"
        );

        Student student2 = createStudent(
                "Sara Ahmed",
                "sara@gmail.com",
                LocalDate.of(1999, 3, 15),
                "+923001112233"
        );

        studentService.addStudent(student1);
        studentService.addStudent(student2);

        boolean condition = studentService.getAllStudents().size() == 2;

        check("Add two students", condition);
    }

    private static void testViewAllStudents(StudentService studentService) {
        List<Student> students = studentService.getAllStudents();

        boolean condition = students != null && students.size() == 2;

        check("View all students", condition);
    }

    private static void testSearchStudentById(StudentService studentService) {
        Student student = studentService.searchStudentById(1);

        boolean condition = student != null
                && student.getId() == 1
                && student.getName().equals("Ali Khan");

        check("Search student by ID", condition);
    }

    private static void testSearchStudentByName(StudentService studentService) {
        List<Student> matchedStudents = studentService.searchStudentByName("ali");

        boolean condition = matchedStudents.size() == 1
                && matchedStudents.get(0).getName().equals("Ali Khan");

        check("Search student by name with partial/case-insensitive match", condition);
    }

    private static void testUpdateStudent(StudentService studentService) {
        Student updatedStudent = createStudent(
                "Ali Raza",
                "aliraza@gmail.com",
                LocalDate.of(2000, 5, 10),
                "+923126665505"
        );

        studentService.updateStudent(1, updatedStudent);

        Student studentAfterUpdate = studentService.searchStudentById(1);

        boolean condition = studentAfterUpdate != null
                && studentAfterUpdate.getName().equals("Ali Raza")
                && studentAfterUpdate.getEmail().equals("aliraza@gmail.com");

        check("Update student", condition);
    }

    private static void testDeleteStudent(StudentService studentService) {
        studentService.deleteStudent(2);

        boolean condition = studentService.getAllStudents().size() == 1;

        check("Delete student", condition);
    }

    private static void testSearchDeletedStudent(StudentService studentService) {
        Student deletedStudent = studentService.searchStudentById(2);

        boolean condition = deletedStudent == null;

        check("Search deleted student should return null", condition);
    }

    private static Student createStudent(String name, String email, LocalDate dateOfBirth, String mobileNumber) {
        Student student = new Student();

        student.setName(name);
        student.setEmail(email);
        student.setDateOfBirth(dateOfBirth);
        student.setMobileNumber(mobileNumber);

        return student;
    }

    private static void check(String testName, boolean condition) {
        if (condition) {
            passedTests++;
            System.out.println("[PASS] " + testName);
        } else {
            failedTests++;
            System.out.println("[FAIL] " + testName);
        }
    }

    private static void printTestSummary() {
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        System.out.println("Total: " + (passedTests + failedTests));
    }
}
