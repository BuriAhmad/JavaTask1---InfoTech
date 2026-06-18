package com.internship.studentmanagement.service;

import com.internship.studentmanagement.exception.DuplicateEmailException;
import com.internship.studentmanagement.exception.StudentNotFoundException;
import com.internship.studentmanagement.model.Student;
import com.internship.studentmanagement.repository.StudentRepository;
import com.internship.studentmanagement.validator.StudentValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository = new StudentRepository();
    private int nextId = 1;

    @Override
    public void addStudent(Student student) {
        StudentValidator.validateStudent(student);

        if (studentRepository.emailExists(student.getEmail())) {
            throw new DuplicateEmailException("Email already exists.");
        }

        student.setId(nextId);
        nextId++;

        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student searchStudentById(int id) {
        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }

        return student;
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        List<Student> matchedStudents = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                matchedStudents.add(student);
            }
        }

        return matchedStudents;
    }

    @Override
    public void updateStudent(int id, Student updatedStudent) {
        Student existingStudent = searchStudentById(id);

        StudentValidator.validateStudent(updatedStudent);

        if (studentRepository.emailExistsForAnotherStudent(updatedStudent.getEmail(), id)) {
            throw new DuplicateEmailException("Email already exists.");
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        existingStudent.setMobileNumber(updatedStudent.getMobileNumber());
    }

    @Override
    public void deleteStudent(int id) {
        Student student = searchStudentById(id);
        studentRepository.delete(student);
    }
}
