# Student Management Console Application

## Overview

A Java Maven console application for managing student records in memory using `ArrayList`.

## Features

- Add student
- View all students
- Search student by ID
- Search student by name
- Update student
- Delete student
- Auto-generated student IDs
- Duplicate email prevention
- Validation for name, email, date of birth, and mobile number
- Age calculation while displaying student details

## Technologies Used

- Java
- Maven
- Lombok
- IntelliJ IDEA

## Package Structure

- `model`: Student data model
- `repository`: In-memory student storage and lookup
- `service`: Business logic and student operations
- `validator`: Input validation rules
- `exception`: Custom exceptions for validation and lookup errors
- `util`: Date and console input helpers
- `menu`: Console UI and user interaction flow

## Validation Rules

- Name: required, 3 to 50 characters, alphabets and spaces only
- Email: required, valid email format, unique across students
- Date of birth: required, not in the future, age must be between 5 and 60 years
- Mobile number: required, must start with `+92` and then exactly 10 digits

## How to Run

1. Clone or open the project in IntelliJ IDEA.
2. Make sure Java and Maven are installed.
3. Run:
   ```bash
   mvn clean compile
   ```
4. Run `Main.java` from IntelliJ IDEA.

## Sample Usage

```
1. Add Student
2. View All Students
3. Search Student by ID
4. Search Student by Name
5. Update Student
6. Delete Student
7. Exit Application
```

Example:
- Choose `1` to add a student
- Enter name, email, date of birth, and mobile number
- Choose `2` to view the saved records
