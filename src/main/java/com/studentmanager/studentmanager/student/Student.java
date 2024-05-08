package com.studentmanager.studentmanager.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.Nullable;

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

  /* private static Set<Integer> generatedMatriklNumbers = new HashSet<>(); */

  @Id
  @Column(name = "matr_nr")
  private int matrNr;

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
  @Column(name = "active_modules")
  private List<Integer> activeModules;

  @Nullable
  @Column(name = "passed_modules")
  private List<Integer> passedModules;

  @Nullable
  @Column(name = "failed_modules")
  private List<Integer> failedModules;

  public Student(int matrNr, String name, String firstName, LocalDate dob) {
    this.matrNr = matrNr;
    this.name = name;
    this.firstName = firstName;
    this.dob = dob;
  }

  public String addActiveModule(Integer moduleId) {
    if (!passedModules.contains(moduleId)) {
      if (!activeModules.contains(moduleId)) {
        activeModules.add(moduleId);
        return "Student signed up successfully in module with Id: " + moduleId;
      } else {
        return "Student already signed up for module with Id: " + moduleId;
      }
    } else {
      return "Student cannot sign up for module with Id: " + moduleId + " because he already passed it.";
    }

  }

  public String removeAktiveModule(Integer moduleId) {
    if (activeModules.contains(moduleId)) {
      activeModules.remove(moduleId);
      return "Student signed out successfully in module with Id " + moduleId;
    } else {
      return "Student did not signed up in module with id " + moduleId;
    }
  }

  public String passedModule(Integer moduleId) {
    if (activeModules.contains(moduleId) && !passedModules.contains(moduleId)) {

      activeModules.remove(moduleId);
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
    if (activeModules.contains(moduleId)) {
      for (int i : failedModules) {
        if (i == moduleId) {
          trys++;

          if (trys >= 3) {
            activeModules.remove(moduleId);
            failedModules.add(moduleId);
            return "Student failed module with Id: " + moduleId + " for the third time and will now be exmatriculated";
          }
        }
      }
      activeModules.remove(moduleId);
      failedModules.add(moduleId);
      return "Student failed Module with Id: " + moduleId + " for the " + trys + ". time";
    } else if (passedModules.contains(moduleId)) {
      return "Student cannot fail module with Id: " + moduleId + " because he already passed it.";
    } else {
      return "Student cannot fail module with Id: " + moduleId + " because he didn't signed up for it.";
    }

  }

  /*
   * private int generateUniquematrNr() {
   * Random random = new Random();
   * int randommatrNr;
   * 
   * while (matrNrIsUnique == false) {
   * randommatrNr = random.nextInt(1000000) + 1000000;
   * 
   * if (generatedMatriklNumbers.contains(randommatrNr)) {
   * matrNrIsUnique = false;
   * } else {
   * generatedMatriklNumbers.add(randommatrNr);
   * matrNrIsUnique = true;
   * }
   * 
   * }
   * 
   * do {
   * randommatrNr = random.nextInt(1000000) + 1000000;
   * } while (generatedMatriklNumbers.contains(randommatrNr));
   * 
   * generatedMatriklNumbers.add(randommatrNr);
   * 
   * return randommatrNr;
   * }
   */

  <<<<<<<HEAD
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

  =======>>>>>>>66339075387f 2 bc46b51bb985c0c5427ea5d1761
}
