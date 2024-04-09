package com.studentmanager.studentmanager.student;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class Student {

    @Id
    private int matriklNr;
    private String name;
    private String firstName; 
    private LocalDate dob;

}
