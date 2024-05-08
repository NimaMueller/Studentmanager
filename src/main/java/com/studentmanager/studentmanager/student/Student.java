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
    if (!passedModules.contains(moduleId)) {
      if (!aktiveModules.contains(moduleId)) {
        aktiveModules.add(moduleId);
        return "Student signed up successfully in module with Id: " + moduleId;
      } else {
        return "Student already signed up for module with Id: " + moduleId;
      }
    } else {
      return "Student cannot sign up for module with Id: " + moduleId + " because he already passed it.";
    }

  }

  public String removeAktiveModule(Integer moduleId) {
    if (aktiveModules.contains(moduleId)) {
    aktiveModules.remove(moduleId);
    return "Student signed out successfully in module with Id " + moduleId;
  } else {
    return"Student did not signed up in module with id " + moduleId;
  }
  }
  

  public String passedModule(Integer moduleId) {
    if (aktiveModules.contains(moduleId) && !passedModules.contains(moduleId)) {

      aktiveModules.remove(moduleId);
      passedModules.add(moduleId);
      return "Student passed module with Id: " + moduleId;

    } else if (passedModules.contains(moduleId)) {
      return "Student already passed module with Id: " + moduleId;

    } else {
      return "Student cannot pass module with Id: " + moduleId + " because he didn't signed up for it.";

    }

  }

  public String failedModule(Integer moduleId) {
    int trys = 1;
    if (aktiveModules.contains(moduleId)) {
      for (int i : failedModules) {
        if (i == moduleId) {
          trys++;

          if (trys >= 3) {
            aktiveModules.remove(moduleId);
            failedModules.add(moduleId);
            return "Student failed module with Id: " + moduleId + " for the third time and will now be exmatriculated";
          }
        }
      }
      aktiveModules.remove(moduleId);
      failedModules.add(moduleId);
      return "Student failed Module with Id: " + moduleId + " for the " + trys + ". time";
    } else if (passedModules.contains(moduleId)) {
      return "Student cannot fail module with Id: " + moduleId + " because he already passed it.";
    } else {
      return "Student cannot fail module with Id: " + moduleId + " because he didn't signed up for it.";
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
