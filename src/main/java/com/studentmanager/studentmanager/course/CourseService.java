package com.studentmanager.studentmanager.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public String createCourse (Course course) {

        try {

    courseRepository.save(course);
    courseRepository.flush(); 
    return "Course " + course.getDescription() + " created succesfully!";
             }

    catch (Exception e) {
        return "An error occurred while creating a new course with courseId: " + course.getCourseId() + " "
                + e.getMessage();
             }

            }

    public Course getCourse(int courseId) {

        return courseRepository.findByCourseId(courseId);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
        

    public String updateCourse(Course course) {

        try {

            Course c = courseRepository.findByCourseId(course.getCourseId());

            if (course.getDescription() !=null) {
                c.setDescription(course.getDescription());
            }


            if (course.getDegree() != null) {

               c.setDegree(course.getDegree());

            }


            courseRepository.save(c);
            courseRepository.flush();

            return "Course updated successfully: " + c.toString();

        } catch (Exception e) {
            return "An error occurred while updating Course with courseId: " + course.getCourseId() + " "
                    + e.getMessage();
        }
    }

        public String delete(int courseId) {
             courseRepository.delete(courseRepository.findByCourseId(courseId));
             return "Course with courseId " +  courseId + " successfully deleted";
             

        }

    }




