package com.studentmanager.studentmanager.student;

import java.time.LocalDate;

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
  // @Column(name="matriklnr")
  private int matriklNr;

  // @Column(name="name")
  private String name;

  // @Column(name="firstname")
  private String firstName;

  // @Column(name="dob")
  private LocalDate dob;

}
