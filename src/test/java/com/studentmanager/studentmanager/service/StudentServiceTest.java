package com.studentmanager.studentmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.studentmanager.studentmanager.student.Student;
import com.studentmanager.studentmanager.student.StudentRepository;
import com.studentmanager.studentmanager.student.StudentService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StudentServiceTest {



    @InjectMocks
    private StudentService studentService;


    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testUpdateStudent() {
        when(studentRepository.findByMatriklNr(1)).thenReturn(new Student(1111,1, "test", null, null));
        Student studentToUpdate = new Student(1,12, "Mueller", null, null);

        
        
        String result = studentService.updateStudent(studentToUpdate);
       
        assertTrue(result.contains("Mueller"), "Update should be successful and return the updated student's name");
}
    }


    

