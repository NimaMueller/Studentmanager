package com.studentmanager.studentmanager.student;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Student {

  @Id
  @Column(name = "matrikl_nr")
  private int matriklNr;

  @Column(name = "student_course_id")
  private Integer studentCourseId; 

  @Column(name = "name")
  private String name;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "dob")
  private LocalDate dob;

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "course_id")
   * private Course course
   */;

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "student_course_id", insertable = false, updatable =
   * false)
   * private Course course;
   */

}
