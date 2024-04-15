package com.studentmanager.studentmanager.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    ArrayList<Student> studentList = new ArrayList<>();

    public String createStudent(Student student) {

        try {
            studentRepository.save(student);
            studentRepository.flush();
            return "Student: " + student.getFirstName() + " " + student.getName() + " created successfully!";
        } catch (Exception e) {
            System.out.println(
                    "An error occurred while creating a new student with matriklNr: " + e.getMessage());
        }
        return null;
    }

    public Student getStudent(int matriklNr) {
        return studentRepository.findByMatriklNr(matriklNr);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        studentRepository.updateStudentbyMatriklNr(student.getMatriklNr(), student.getName());
        studentRepository.flush();
        /* studentRepository.save(student); */
        return student;
    }

    public String deleteStudent(int matriklNr) {

        studentRepository.delete(studentRepository.findByMatriklNr(matriklNr));

        return "Student with matriklNr: " + matriklNr + " deleted successfully!";
    }

}
