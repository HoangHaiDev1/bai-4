package com.sqc.employee.model;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
     UUID id;
     String name;
     LocalDate birthDate;
     Gender gender;
     int salary;
     String phoneNumber;

    // Enum for gender
    public enum Gender {
        MALE, FEMALE, OTHER
    }

}
