package com.studentmanager.studentmanager.student;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

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

    public boolean IsEligibleForBachelor(int matriklNr) throws IOException, InterruptedException {

 
        String uri =  "http://localhost:8080/api/v1/module/calculateCp";

        Gson gson = new Gson(); 
        String jsonBody = gson.toJson(Arrays.asList(1,2,3));
        HttpRequest request = HttpRequest.newBuilder()                
        .uri(URI.create(uri)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(jsonBody)).build();

        HttpResponse<String> bachelorResponse = client.send(request, BodyHandlers.ofString());

        if (bachelorResponse.body().isEmpty() || bachelorResponse.body().isBlank()) {
            throw new NullPointerException("Student does not passed any modules");
        } else {
                 return false;
            

        }}


    
    

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
            StringBuilder courseBuilder = new StringBuilder();

            // Coursemanager API call (Check if Module available for the Students Course).
            String courseUri = "http://localhost:8081/api/v1/course/checkForModule/";
            courseBuilder.append(courseUri)
                    .append(studentRepository.findByMatrNr(matrNr).getStudentCourseId() + "/" + modulId);
            HttpRequest courseRequest = HttpRequest.newBuilder().uri(new URI(courseBuilder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().isEmpty() || response.body().isBlank()) {
                throw new NullPointerException("Module not available");
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


    public String signOutForModule(int matriklNr, Integer moduleId) {
      String signOut = studentRepository.findByMatriklNr(matriklNr).removeAktiveModule(moduleId);
      studentRepository.save(studentRepository.findByMatriklNr(matriklNr));
      studentRepository.flush();
      return signOut;
                
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

    public List<Integer> tempModuleList; 

    // Get the Module list for the specific course
    public String getModuleList(int matriklNr, int courseId) {
        try {
            tempModuleList = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            String uri = "http://localhost:8081/api/v1/course/getModuleList/";
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
