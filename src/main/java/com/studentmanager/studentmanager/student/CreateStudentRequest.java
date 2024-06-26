package com.studentmanager.studentmanager.student;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStudentRequest {

    private String name;

    private String firstName;

    private LocalDate dob;

}
