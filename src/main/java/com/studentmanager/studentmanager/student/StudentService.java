package com.studentmanager.studentmanager.student;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class StudentService {

    @Value("${module.manager.host}")
    private String moduleManagerHost;

    @Value("${course.manager.host}")
    private String courseManagerHost;

    @Autowired
    StudentRepository studentRepository;

    HttpClient client = HttpClient.newBuilder().build();

    // Create a new Student
/*     public String createStudent(Student student) {
        try {
            studentRepository.save(student);
            studentRepository.flush();
            return "Student created successfully: " + student.toString();
        } catch (Exception e) {
            return "An error occurred while creating a new student with matrNr: " + student.getMatrNr() + " "
                    + e.getMessage();
        }
    } */

    public String createStudent(CreateStudentRequest request) {
        try {
            // Neues Student-Objekt erstellen
            Student student = new Student();
            
            // Attribute aus dem Request-Objekt setzen
            student.setName(request.getName());
            student.setFirstName(request.getFirstName());
            student.setDob(request.getDob());
    
            // Student-Objekt speichern
            studentRepository.save(student);
            studentRepository.flush();
    
            return "Student created successfully: " + student.toString();
        } catch (Exception e) {
            return "An error occurred while creating a new student: " + e.getMessage();
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
    public String enroll(int matrNr, Integer courseId) throws IOException, InterruptedException, URISyntaxException {
         try {
        // Coursemanager API call
        StringBuilder builder = new StringBuilder();
        String uri = "/api/v1/course/checkForCourse/";
        builder.append(courseManagerHost).append(uri).append(courseId);
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
          return "An error occurred while enrolling in course with id: " + courseId +
          " "
          + e.getMessage();
          }
         
    }

    // Student signed up for a Module.
    public String signUpForModule(int matrNr, String moduleId)
            throws URISyntaxException, IOException, InterruptedException {
        try {
            // Modulemanager API call (GET Module).
            StringBuilder moduleBuilder = new StringBuilder();
            String moduleUri = "/api/v1/module/checkForModule/";
            moduleBuilder.append(moduleManagerHost).append(moduleUri).append(moduleId);
            HttpRequest moduleRequest = HttpRequest.newBuilder().uri(new URI(moduleBuilder.toString()))
                    .GET().build();
            HttpResponse<String> moduleResponse = client.send(moduleRequest, BodyHandlers.ofString());

            // Coursemanager API call (Check if Module available for the Students Course).
            StringBuilder courseBuilder = new StringBuilder();
            String courseUri = "/api/v1/course/checkForModule/";
            courseBuilder.append(courseManagerHost).append(courseUri)
                    .append(studentRepository.findByMatrNr(matrNr).getStudentCourseId() + "/" + moduleId);
            HttpRequest courseRequest = HttpRequest.newBuilder().uri(new URI(courseBuilder.toString()))
                    .GET().build();
            HttpResponse<String> courseResponse = client.send(courseRequest, BodyHandlers.ofString());

            // Check if Student is enrolled in a Course.
            if (studentRepository.findByMatrNr(matrNr).getStudentCourseId() == null) {
                throw new NullPointerException(
                        "Student is not enrolled in a Course and therefor cannot sign up for any Modules.");
            } else if (moduleResponse.body().equals("false")) { // Check if Module exists.
                throw new NullPointerException("Module not available!");
            }
            // Check if Module is available for this Course.
            if (courseResponse.body().equals("false")) {
                throw new NullPointerException("Module is not available for this Course!");
            } else {
                String statusMessage = studentRepository.findByMatrNr(matrNr).addActiveModule(moduleId);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
        } catch (

        Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + moduleId + " " +
                    e.getMessage();
        }
    }

    // Student passed a Module.
    public String passedModule(int matrNr, String moduleId, double grade) {
        try {
            StringBuilder builder = new StringBuilder();
            String uri = "/api/v1/module/";
            builder.append(moduleManagerHost).append(uri).append(moduleId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().isEmpty() || response.body().isBlank()) {
                throw new NullPointerException("Module not available");
            } else {
                String statusMessage = studentRepository.findByMatrNr(matrNr).passedModule(moduleId, grade);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + " "
                    + e.getMessage();
        }
    }

    public String moduleResult(int matrNr, String moduleId, double grade) {
        try {
            StringBuilder builder = new StringBuilder();
            String uri = "/api/v1/module/";
            builder.append(moduleManagerHost).append(uri).append(moduleId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().isEmpty() || response.body().isBlank()) {
                throw new NullPointerException("Module not available");
            } else if (grade < 4.1) {
                String statusMessage = studentRepository.findByMatrNr(matrNr).passedModule(moduleId, grade);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
            else {
                String statusMessage = studentRepository.findByMatrNr(matrNr).failedModule(moduleId, grade);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + " "
                    + e.getMessage();
        }
    }

/*     // Student failed a Module.
    public String failedModule(int matrNr, Integer moduleId) {
        try {
            StringBuilder builder = new StringBuilder();
            String uri = "/api/v1/module/checkForModule";
            builder.append(moduleManagerHost).append(uri).append(moduleId);
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(builder.toString()))
                    .GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.body().equals("false")) {
                throw new NullPointerException("Module not available");
            } else {
                String statusMessage = studentRepository.findByMatrNr(matrNr).failedModule(moduleId);
                studentRepository.save(studentRepository.findByMatrNr(matrNr));
                studentRepository.flush();
                return statusMessage;
            }
        } catch (Exception e) {
            return "An error occurred while trying to sign up for Module with ID: " + moduleId + " "
                    + e.getMessage();
        }
    } */

    public String IsEligibleForBachelor(int matrNr) throws IOException, InterruptedException {
        Student studentLookingForBachelor = getStudent(matrNr);
        if (studentLookingForBachelor != null) {

            // Get passed modules from student
            List<String> passedModules = new ArrayList<String>(); 
            passedModules.addAll(studentLookingForBachelor.getPassedModules().keySet());

            // Build request for ModuleManager
            String uri = "http://modulemanager-service:8080/api/v1/module/calculateCp";
            Gson gson = new Gson();
            String jsonBody = gson.toJson(passedModules);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri)).header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody)).build();

            // Retrieve response from ModuleManager
            HttpResponse<String> bachelorResponse = client.send(request, BodyHandlers.ofString());

            // Logic with received ModuleList
            if (bachelorResponse.body().isEmpty() || bachelorResponse.body().isBlank()) {
                throw new NullPointerException("Student hast not passed any modules");
            } else {
                String body = bachelorResponse.body();

                // Convert String into Integer
                int cp;
                try {
                    cp = Integer.parseInt(body);
                } catch (NumberFormatException e) {
                    return "Parsing to Integer failed because: " + e.getMessage();
                }
                if (cp >= 150) {
                    return "Student with MatrNr: " + matrNr + " is ready for bachelor and has: " + cp
                            + " credit points";
                } else {
                    return "Student with MatrNr: " + matrNr + " is not ready for bachelor and has: " + cp
                            + " credit points";
                }
            }
        } else {
            return "Student does not exist! Calculation not possible";
        }
    }

    public String signOutOfModule(int matrNr, String moduleId) {
        String signOut = studentRepository.findByMatrNr(matrNr).removeActiveModule(moduleId);
        studentRepository.save(studentRepository.findByMatrNr(matrNr));
        studentRepository.flush();
        return signOut;
    }

}