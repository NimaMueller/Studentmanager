package com.studentmanager.studentmanager.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public static final String URL = "spring.datasource.url=jdbc:postgresql://localhost:5438";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";

    @Autowired
    ArrayList<Student> studentList;

    public Student getStudentById(int matriklNr) {
        studentList.add(new Student(1394618, "Mueller", "Nima", LocalDate.of(2003, 01, 17)));
        try {
            for (Student student : studentList) {
                if (matriklNr == student.getMatriklNr()) {
                    return student;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(
                    "An error occurred while retrieving the student with matriklNr: " + matriklNr + e.getMessage());
        }
        return null;

    }

    public String createStudent(Student student) {

        String sql = "INSERT INTO students (matriklNr, name, firstname, dob)";

        try (
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // Setzen Sie die Parameter basierend auf den Eigenschaften Ihres Objekts
            pstmt.setString(1, student.getProperty1());
            pstmt.setString(2, student.getProperty2());

            // Führen Sie die SQL-Insert-Anweisung aus
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        studentList.add(student);

        return "Student: " + student.getFirstName() + " " + student.getName() + " created successfully!";
    }

    public Student updaStudent(Student student, int matriklNr) {
        studentList.remove(getStudentById(matriklNr));
        studentList.add(student);

        return student;
    }

    public String deleteStudent(int matriklNr) {

        studentList.remove(getStudentById(matriklNr));

        return "Student " + " deleted successfully!";
    }

}
