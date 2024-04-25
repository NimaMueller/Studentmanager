package com.studentmanager.studentmanager.student;

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
  @GetMapping("{matriklNr}")
  public Student getStudent(@PathVariable int matriklNr) {
    return studentService.getStudent(matriklNr);
  }

  // Get every Stundent in the DB.
  @GetMapping("getAll")
  public List<Student> showAll() {
    return studentService.getAllStudents();
  }

  // Update a Stundent in the DB.
/*   @PutMapping("put")
  public String updateStudent(@RequestBody Student student) {
    return studentService.updateStudent(student);
  } */

  // Delete a Stundent from the DB.
  @DeleteMapping("delete/{matriklNr}")
  public String deleteStudent(@PathVariable int matriklNr) {
    return studentService.deleteStudent(matriklNr);
  }

  @PostMapping("enroll")
  public String enrollCourse(@RequestParam int courseId) {

    return "entity";
  }
/* 
  @PostMapping("signUp")
  public String signUp(@RequestParam int moduleId) {

    return studentService.signUpForModule(moduleId);
  } */

}
