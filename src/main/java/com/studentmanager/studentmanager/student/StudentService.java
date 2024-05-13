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

    // Create a new Student
    public String createStudent(Student student) {
        try {
            studentRepository.save(student);
            studentRepository.flush();
            return "Student created successfully: " + student.toString();
        } catch (Exception e) {
            return "An error occurred while creating a new student with matrNr: " + student.getMatrNr() + " "
                    + e.getMessage();
        }
    }

    // Get a Student by his matriculation number
    public Student getStudent(int matrNr) {
        return studentRepository.findByMatrNr(matrNr);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String updateStudent(Student student) {
        try {
            Student s = studentRepository.findByMatrNr(student.getMatrNr());

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

            return "Student updated successfully!: " + s.toString();

        } catch (Exception e) {
            return "An error occurred while updating student with matrNr: " + student.getMatrNr() + " "
                    + e.getMessage();
        }
    }

    public String deleteStudent(int matrNr) {
        studentRepository.delete(studentRepository.findByMatrNr(matrNr));

        return "Student with matrNr: " + matrNr + " deleted successfully!";
    }

    // Enroll Student in a Course.
    public String enroll(int matrNr, Integer courseId) {
        try {
            // Coursemanager API call
            StringBuilder builder = new StringBuilder();
            String uri = "http://localhost:8081/api/v1/course/checkForCourse/";
            builder.append(uri).append(courseId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().equals("false")) {
                throw new NullPointerException("Course not available");
            } else {
                studentRepository.findByMatrNr(matrNr).setStudentCourseId(courseId);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return "Student enrolled successfully in course: " + courseId;
            }
        } catch (Exception e) {
            return "An error occurred while enrolling in course with id: " + courseId + " "
                    + e.getMessage();
        }
    }

    // Student signed up for a Module.
    public String signUpForModule(int matrNr, Integer modulId) {
        try {
            // Modulemanager API call (GET Module).
            StringBuilder moduleBuilder = new StringBuilder();
            String moduleUri = "http://localhost:8080/api/v1/module/checkForModule/";
            moduleBuilder.append(moduleUri).append(modulId);
            HttpRequest moduleRequest = HttpRequest.newBuilder().uri(new URI(moduleBuilder.toString()))
                    .GET().build();
            HttpResponse<String> moduleResponse = client.send(moduleRequest, BodyHandlers.ofString());

            // Coursemanager API call (Check if Module available for the Students Course).
            StringBuilder courseBuilder = new StringBuilder();
            String courseUri = "http://localhost:8081/api/v1/course/checkForModule/";
            courseBuilder.append(courseUri)
                    .append(studentRepository.findByMatrNr(matrNr).getStudentCourseId() + "/" + modulId);
            HttpRequest courseRequest = HttpRequest.newBuilder().uri(new URI(courseBuilder.toString()))
                    .GET().build();
            HttpResponse<String> courseResponse = client.send(courseRequest, BodyHandlers.ofString());

            // Check if Module exists.
            if (moduleResponse.body().equals("false")) {
                throw new NullPointerException("Module not available!");
            }
            // Check if Module is available for this Course.
            else if (courseResponse.body().equals("false")) {
                throw new NullPointerException("Module is not available for this Course!");
            } else {
                String statusMessage = studentRepository.findByMatrNr(matrNr).addActiveModule(modulId);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + modulId + " " +
                    e.getMessage();
        }
    }

    // Student passed a Module.
    public String passedModule(int matrNr, Integer modulId) {
        try {
            StringBuilder builder = new StringBuilder();
            String uri = "http://localhost:8080/api/v1/module/";
            builder.append(uri).append(modulId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().isEmpty() || response.body().isBlank()) {
                throw new NullPointerException("Module not available");
            } else {
                String statusMessage = studentRepository.findByMatrNr(matrNr).passedModule(modulId);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + " "
                    + e.getMessage();
        }
    }

    // Student failed a Module.
    public String failedModule(int matrNr, Integer modulId) {
        try {
            StringBuilder builder = new StringBuilder();
            String uri = "http://localhost:8080/api/v1/module/";
            builder.append(uri).append(modulId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().isEmpty() || response.body().isBlank()) {
                throw new NullPointerException("Module not available");
            } else {
                String statusMessage = studentRepository.findByMatrNr(matrNr).failedModule(modulId);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + " "
                    + e.getMessage();
        }
    }

}