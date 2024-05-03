package com.studentmanager.studentmanager.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Student {

  /* private static Set<Integer> generatedMatriklNumbers = new HashSet<>(); */

  @Id
  @Column(name = "matrikl_nr")
  private int matriklNr;

  @Nullable
  @Column(name = "student_course_id")
  private Integer studentCourseId;
  
  @Nullable
  @Column(name = "module_course_id")
  private Integer studentModuleId;
  

  @Column(name = "name")
  private String name;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "dob")
  private LocalDate dob;

  @Nullable
  @Column(name = "aktive_modules")
  private List<Integer> aktiveModules;

  @Nullable
  @Column(name = "passed_modules")
  private List<Integer> passedModules;

  @Nullable
  @Column(name = "failed_modules")
  private List<Integer> failedModules;

  public Student(int matriklNr, String name, String firstName, LocalDate dob) {
    this.matriklNr = matriklNr;
    this.name = name;
    this.firstName = firstName;
    this.dob = dob;
  }

public String addAktiveModule(Integer moduleId) {
    if (!aktiveModules.contains(moduleId)) {
      aktiveModules.add(moduleId);
      return "Student signed up successfully in module: " + moduleId;
    } else {
      return "already signed up for module: " + moduleId;
    }
  }


  public String addPassedModule(Integer moduleId) {

    if (aktiveModules.contains(moduleId) && !failedModules.contains(moduleId)) {

      aktiveModules.remove(moduleId);
      passedModules.add(moduleId);
      return "Student passed Module " + moduleId; 
    }
    else {
      return "Student is not signed up for " + moduleId;
    }
  }

  public String addFailedModule(Integer moduleId) {

    if (aktiveModules.contains(moduleId) && !passedModules.contains(moduleId)) {

      aktiveModules.remove(moduleId);
      failedModules.add(moduleId);
      return "Student failed Module " + moduleId; 
    }
    else {
      return "Student already failed  " + moduleId;
    }
  }

  /*
   * private int generateUniqueMatriklNr() {
   * Random random = new Random();
   * int randomMatriklNr;
   * 
   * while (matriklNrIsUnique == false) {
   * randomMatriklNr = random.nextInt(1000000) + 1000000;
   * 
   * if (generatedMatriklNumbers.contains(randomMatriklNr)) {
   * matriklNrIsUnique = false;
   * } else {
   * generatedMatriklNumbers.add(randomMatriklNr);
   * matriklNrIsUnique = true;
   * }
   * 
   * }
   * 
   * do {
   * randomMatriklNr = random.nextInt(1000000) + 1000000;
   * } while (generatedMatriklNumbers.contains(randomMatriklNr));
   * 
   * generatedMatriklNumbers.add(randomMatriklNr);
   * 
   * return randomMatriklNr;
   * }
   */

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "course_id")
   * private Course course
   */

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "student_course_id", insertable = false, updatable =
   * false)
   * private Course course;
   */

}
