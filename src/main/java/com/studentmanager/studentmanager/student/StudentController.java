package com.studentmanager.studentmanager.student;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

  @Autowired
  StudentService studentService;

  // Create a new Stundent and save him to the DB.
  @PostMapping("post")
  public String createStudent(@RequestBody Student student) {
    return studentService.createStudent(student);
  }

  // Get a Stundent in the DB by his Matrikle number.
  @GetMapping("{matrNr}")
  public Student getStudent(@PathVariable int matrNr) {
    return studentService.getStudent(matrNr);
  }

  // Get every Stundent in the DB.
  @GetMapping("getAll")
  public List<Student> showAll() {
    return studentService.getAllStudents();
  }

  // Update a Stundent in the DB.
  @PutMapping("put")
  public String updateStudent(@RequestBody Student student) {
    return studentService.updateStudent(student);
  }

  // Delete a Stundent from the DB.
  @DeleteMapping("delete/{matrNr}")
  public String deleteStudent(@PathVariable int matrNr) {
    return studentService.deleteStudent(matrNr);
  }

  // Enroll a Student in a Course.
  @PostMapping("enroll")
  public String enrollInCourse(@RequestParam int matrNr, Integer courseId) {
    return studentService.enroll(matrNr, courseId);
  }

  // Let the Student sign up for a Module of his Course.
  @PostMapping("signUp")
  public String signUp(@RequestParam int matrNr, Integer moduleId) {
    return studentService.signUpForModule(matrNr, moduleId);
  }

  @DeleteMapping("signOut")
  public String signOut(@RequestParam int matriklNr, Integer moduleId) {

    return studentService.signOutForModule(matriklNr, moduleId);
  }

  // Let the Student pass a Module.
  @PostMapping("pass")
  public String passed(@RequestParam int matrNr, Integer moduleId) {
    return studentService.passedModule(matrNr, moduleId);
  }

  // Let the Student fail a Module.
  @PostMapping("fail")
  public String failed(@RequestParam int matrNr, Integer moduleId) {
    return studentService.failedModule(matrNr, moduleId);
  }

  @GetMapping("getPassedList/{matriklNr}")
  public List<Integer> getPassedList(@PathVariable Integer matriklNr) {
    return studentService.getPassedModulesByMatriklNr(matriklNr);
     
  }

  @GetMapping("eligibileBachelor/{matriklNr}")
  public boolean eligilbleBachelor(@PathVariable Integer matriklNr) throws IOException, InterruptedException {
    return studentService.IsEligibleForBachelor(matriklNr);
     
  }
  

}
