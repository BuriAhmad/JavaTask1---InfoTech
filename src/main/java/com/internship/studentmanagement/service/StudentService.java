package com.internship.studentmanagement.service;

import com.internship.studentmanagement.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    List<Student> getAllStudents();

    Student searchStudentById(int id);

    List<Student> searchStudentByName(String name);

    void updateStudent(int id, Student updatedStudent);

    void deleteStudent(int id);
}
