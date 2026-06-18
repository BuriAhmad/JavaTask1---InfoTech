package com.internship.studentmanagement.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

    private DateUtil() {
    }

    public static int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
