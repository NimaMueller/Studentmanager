package com.studentmanager.studentmanager.course;

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
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("post")
    public String createCourse(@RequestBody Course course) {

        return courseService.createCourse(course);
    }

    @GetMapping("get/{courseId}")
    public Course getCourse(@PathVariable int courseId) {

        return courseService.getCourse(courseId);
    }

    @GetMapping("getAll")
    public List<Course> showAll() {
        return courseService.getAllCourses();
    }

    @DeleteMapping("delete/{courseId}")
    public String delete(@PathVariable int courseId) {
        return courseService.delete(courseId);
    }

    @PutMapping("put")
    public String updateCourse(@RequestBody Course course) {

        return courseService.updateCourse(course);
    }

}
