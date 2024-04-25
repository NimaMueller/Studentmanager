package com.studentmanager.studentmanager.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

 


    public String createStudent(Student student) {

        try {
            studentRepository.save(student);
            studentRepository.flush();
            return "Student created successfully: " + student.toString();
        } catch (Exception e) {
            return "An error occurred while creating a new student with matriklNr: " + student.getMatriklNr() + " "
                    + e.getMessage();
        }
    }

    public Student getStudent(int matriklNr) {
        return studentRepository.findByMatriklNr(matriklNr);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /* public String updateStudent(Student student) {

        try {

            Student s = studentRepository.findByMatriklNr(student.getMatriklNr());

            if (student.getName() != null) {

                s.setName(student.getName());

            }

            if (student.getFirstName() != null) {

                s.setFirstName(student.getFirstName());

            }

            if (student.getDob() != null) {

                s.setDob(student.getDob());
            }

            if (student.getStudentCourseId() != null) {

                s.setStudentCourseId(student.getStudentCourseId());
            }

            // studentRepository.updateStudentbyMatriklNr(student.getMatriklNr(),
            // student.getName());

            studentRepository.save(s);
            studentRepository.flush();

            return "Student updated successfully: " + s.toString();

        } catch (Exception e) {
            return "An error occurred while updating student with matriklNr: " + student.getMatriklNr() + " "
                    + e.getMessage();
        }

    } */

    public String deleteStudent(int matriklNr) {

        studentRepository.delete(studentRepository.findByMatriklNr(matriklNr));

        return "Student with matriklNr: " + matriklNr + " deleted successfully!";
    }

    // Student belegt ein modul seines Studiengangs
/*     public String signUpForModule(int modulId) {
        try {
            moduleRepository.findByModuleId(modulId);
            return "Successfully signed up for: ";
        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + " "
                    + e.getMessage();
        }
    } */





    public int calculator(int x, int y) {

        return x + y;
    }

  

}
