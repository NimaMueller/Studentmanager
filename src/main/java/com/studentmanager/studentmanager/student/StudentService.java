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

/* 
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

    } */

    public Student getStudent(int matriklNr){
        return studentRepository.findByMatriklNr(matriklNr);
    }

    public String createStudent(Student student) {

        studentRepository.save(student);

        return "Student: " + student.getFirstName() + " " + student.getName() + " created successfully!";
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

/*     public Student updateStudent(Student student, int matriklNr) {
        studentList.remove(studentRepository.findByMatriklNr(matriklNr));
        studentList.add(student);

        return student;
    }

    public String deleteStudent(int matriklNr) {

        studentList.remove(studentRepository.findByMatriklNr(matriklNr));

        return "Student " + " deleted successfully!";
    } */

}
