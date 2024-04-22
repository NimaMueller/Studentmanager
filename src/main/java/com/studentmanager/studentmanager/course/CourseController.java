package com.studentmanager.studentmanager.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    CourseService courseService;



    @PostMapping("post")
    public String createCourse(@RequestBody Course course) {

        return courseService.createCourse(course);
    }

    @GetMapping("get")
    public Course getCourse(@PathVariable int coruseId) {

        return courseService.getCourse(coruseId);
    }

    @GetMapping("getAll")
    public List<Course> showAll() {
        return courseService.getAllCourses();
    }
   

    @DeleteMapping("delete")
    public String delete (@PathVariable int courseId) {
        return courseService.delete(courseId);
    }
    
    @PutMapping("put")
    public String updateCourse(@RequestBody Course course) {

        return courseService.updateCourse(course);
    }
   
    
}
