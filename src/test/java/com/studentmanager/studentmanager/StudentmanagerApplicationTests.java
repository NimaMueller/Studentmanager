package com.studentmanager.studentmanager;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.studentmanager.studentmanager.student.StudentService;

@SpringBootTest
class StudentmanagerApplicationTests {

	@Test
	void contextLoads() {
	}

@Test
	void twoPlustwoEqualFour() {
		var calculator = new StudentService();
		assertEquals(5,calculator.calculator(2, 2));
	}

	@Test
	void threePlusFiveEqualEight() {
		var calculator = new StudentService();
		assertEquals(8, calculator.calculator(3, 5));
	}


	
}

	
