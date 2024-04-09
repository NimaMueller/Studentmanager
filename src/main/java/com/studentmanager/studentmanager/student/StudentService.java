package com.studentmanager.studentmanager.student;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

   
        ArrayList<Student> studentList = new ArrayList<>();
    

    public Student getStudentById(int matriklNr) {
        studentList.add(new Student(1394618, "Mueller", "Nima", LocalDate.of(2003, 01, 17)));
        try {
            for (Student student : studentList) {
                if (matriklNr == student.getMatriklNr()) {
                    return student;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(
                    "An error occurred while retrieving the student with matriklNr: " + matriklNr + e.getMessage());
        }
        return null;

    }

    public String createStudent(Student student) {

        return "Student: " + student.getFirstName() + " " + student.getName() + " created successfully!";
    }

    public String deleteStudent(int matriklNr) {

        return "Student " + " deleted successfully!";
    }

}
