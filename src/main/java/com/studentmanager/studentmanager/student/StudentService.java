package com.studentmanager.studentmanager.student;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    HttpClient client = HttpClient.newBuilder().build();

    public String createStudent(Student student) {

        try {

            studentRepository.save(student);
            studentRepository.flush();
            return "Student created successfully: " + student.toString();

        } catch (

        Exception e) {
            return "An error occurred while creating a new student with matriklNr: " + student.getMatriklNr() + " "
                    + e.getMessage();
        }
    }

    // Student schreibt sich für einen Studiengang ein.
    public String enroll(int matriklNr, Integer courseId) {

        try {

            StringBuilder builder = new StringBuilder();
            String uri = "http://localhost:8081/api/v1/course/get/";
            builder.append(uri).append(courseId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().isEmpty() || response.body().isBlank()) {
                throw new NullPointerException("Course not available");
            } else {
                studentRepository.findByMatriklNr(matriklNr).setStudentCourseId(courseId);
                studentRepository.save(studentRepository.findByMatriklNr(matriklNr));
                studentRepository.flush();
                return "Student enrolled successfully in course: " + courseId;
            }
        } catch (Exception e) {
            return "An error occurred while enrolling in course with id: " + matriklNr + " "
                    + e.getMessage();
        }
    }

    // Student belegt ein Modul seines Studiengangs.
    public String signUpForModule(int matriklNr, Integer modulId) {
        try {

            StringBuilder builder = new StringBuilder();
            String uri = "http://localhost:8080/api/v1/module/get/";
            builder.append(uri).append(modulId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().isEmpty() || response.body().isBlank()) {
                throw new NullPointerException("Module not available");
            } else {
                String statusMessage = studentRepository.findByMatriklNr(matriklNr).addAktiveModule(modulId);
                studentRepository.save(studentRepository.findByMatriklNr(matriklNr));
                studentRepository.flush();
                return statusMessage;

            }

        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + " "
                    + e.getMessage();
        }
    }

    public Student getStudent(int matriklNr) {
        return studentRepository.findByMatriklNr(matriklNr);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String updateStudent(Student student) {

        try {

            // studentRepository.updateStudentbyMatriklNr(student.getMatriklNr(),
            // student.getName());

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

            studentRepository.save(s);
            studentRepository.flush();

            return "Student updated successfully: " + s.toString();

        } catch (Exception e) {
            return "An error occurred while updating student with matriklNr: " + student.getMatriklNr() + " "
                    + e.getMessage();
        }

    }

    public String deleteStudent(int matriklNr) {

        studentRepository.delete(studentRepository.findByMatriklNr(matriklNr));

        return "Student with matriklNr: " + matriklNr + " deleted successfully!";
    }

}
