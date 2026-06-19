package com.internship.studentmanagement.validator;

import com.internship.studentmanagement.exception.ValidationException;
import com.internship.studentmanagement.model.Student;
import com.internship.studentmanagement.util.DateUtil;

import java.time.LocalDate;

public class StudentValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)+$";
    private static final String NAME_REGEX = "^[A-Za-z]+(?: [A-Za-z]+)*$";
    private static final String MOBILE_NUMBER_REGEX = "^\\+92\\d{10}$";

    private StudentValidator() {
    }

    public static void validateStudent(Student student) {
        validateName(student.getName());
        validateEmail(student.getEmail());
        validateDateOfBirth(student.getDateOfBirth());
        validateMobileNumber(student.getMobileNumber());
    }

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name is required.");
        }

        if (name.length() < 3 || name.length() > 50) {
            throw new ValidationException("Name must be between 3 and 50 characters.");
        }

        if (!name.matches(NAME_REGEX)) {
            throw new ValidationException("Name must contain only alphabets and single spaces between words.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("Email is required.");
        }

        if (!email.matches(EMAIL_REGEX)) {
            throw new ValidationException("Invalid email format. Use something like name@example.com.");
        }
    }

    public static void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new ValidationException("Date of birth is required.");
        }

        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new ValidationException("Date of birth cannot be in the future.");
        }

        int age = DateUtil.calculateAge(dateOfBirth);

        if (age < 5 || age > 60) {
            throw new ValidationException("Age must be between 5 and 60 years.");
        }
    }

    public static void validateMobileNumber(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new ValidationException("Mobile number is required.");
        }

        if (!mobileNumber.matches(MOBILE_NUMBER_REGEX)) {
            throw new ValidationException("Mobile number must follow the format +92XXXXXXXXXX.");
        }
    }
}
