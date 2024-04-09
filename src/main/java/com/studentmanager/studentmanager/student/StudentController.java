package com.studentmanager.studentmanager.student;

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

    StudentService studentService;

    @GetMapping("{id}")
    public Student getStudent(@PathVariable int matriklNr) {
        return studentService.getStudentById(matriklNr);
    }

    @PostMapping("postStudent")
    public String createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("putStudent/{id}")
    public Student putMethodName(@PathVariable int matriklNr, @RequestBody Student student) {
        return student;
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable int matriklNr) {
        return studentService.deleteStudent(matriklNr);
    }

}
