package com.internship.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String mobileNumber;
}
