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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

  @Autowired
  StudentService studentService;

  @PostMapping("post")
  public String createStudent(@RequestBody Student student) {
    return studentService.createStudent(student);
  }

  @GetMapping("{matriklNr}")
  public Student getStudent(@PathVariable int matriklNr) {
    return studentService.getStudent(matriklNr);
  }

  @GetMapping("getAll")
  public List<Student> showAll() {
    return studentService.getAllStudents();
  }

  @PutMapping("put")
  public Student updateStudent(@RequestBody Student student) {
    return studentService.updateStudent(student);
  }

  @DeleteMapping("delete/{matriklNr}")
  public String deleteStudent(@PathVariable int matriklNr) {
    return studentService.deleteStudent(matriklNr);
  }

}
