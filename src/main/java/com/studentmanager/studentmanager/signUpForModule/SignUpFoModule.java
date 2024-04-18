package com.studentmanager.studentmanager.signUpForModule;

import com.studentmanager.studentmanager.student.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SignUpFoModule {

    @Id
    long id;

 /*    @ManyToOne
    @JoinColumn(name = "matrikl_nr")
    Student student;

    @ManyToOne
    @JoinColumn(name = "moule_id")
    Module module;

    double grade;
    boolean passed;
    int attempt;
 */
}
