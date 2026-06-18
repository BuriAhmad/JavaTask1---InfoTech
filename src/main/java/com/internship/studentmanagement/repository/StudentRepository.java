package com.internship.studentmanagement.repository;

import com.internship.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return students;
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public void delete(Student student) {
        students.remove(student);
    }

    public boolean emailExists(String email) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public boolean emailExistsForAnotherStudent(String email, int currentStudentId) {
        for (Student student : students) {
            if (student.getId() != currentStudentId && student.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }
}
