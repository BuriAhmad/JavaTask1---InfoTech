package com.internship.studentmanagement.util;

import com.internship.studentmanagement.exception.ValidationException;
import com.internship.studentmanagement.validator.StudentValidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtil {

    private final Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    public LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException exception) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
    }

    public String readValidName(String message) {
        while (true) {
            String name = readLine(message);
            try {
                StudentValidator.validateName(name);
                return name;
            } catch (ValidationException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    public String readValidEmail(String message) {
        while (true) {
            String email = readLine(message);
            try {
                StudentValidator.validateEmail(email);
                return email;
            } catch (ValidationException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    public String readValidMobileNumber(String message) {
        while (true) {
            String mobileNumber = readLine(message);
            try {
                StudentValidator.validateMobileNumber(mobileNumber);
                return mobileNumber;
            } catch (ValidationException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }
}
