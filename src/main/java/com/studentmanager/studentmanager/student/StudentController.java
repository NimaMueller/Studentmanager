package com.studentmanager.studentmanager.student;

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

    StudentService studentService = new StudentService();

    @GetMapping("{matriklNr}")
    public Student getStudent(@PathVariable int matriklNr) {
        return studentService.getStudentById(matriklNr);
    }

    @PostMapping("post")
    public String createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("put/{matriklNr}")
    public Student updateStudent(@PathVariable int matriklNr, @RequestBody Student student) {
        studentService.updaStudent(student, matriklNr);
        return student;
    }

    @DeleteMapping("delete/{matriklNr}")
    public String deleteStudent(@PathVariable int matriklNr) {
        return studentService.deleteStudent(matriklNr);
    }

}
