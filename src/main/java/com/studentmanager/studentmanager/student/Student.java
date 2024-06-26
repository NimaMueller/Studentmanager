package com.studentmanager.studentmanager.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "students")
@Getter
@Setter
@ToString
@Entity
public class Student {

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
  private List<String> activeModules;

  @Nullable
  @Column(name = "passed_modules")
  @Convert(converter = HashMapConverter.class)
  private HashMap<String, Double> passedModules;

  @Nullable
  @Column(name = "failed_modules")
  @Convert(converter = HashMapConverter.class)
  private HashMap<String, ArrayList<Double>> failedModules;

  public Student() {
    Random dice = new Random();
    int lowerBound = 1000001;
    int upperBound = 1999999;
    this.matrNr = lowerBound + dice.nextInt(upperBound - lowerBound);
    this.activeModules = new ArrayList<String>();
    this.failedModules = new HashMap<String, ArrayList<Double>>();
    this.passedModules = new HashMap<String, Double>();
  }

  public String addActiveModule(String moduleId) {
    if (!passedModules.containsKey(moduleId)) {
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

  public String removeActiveModule(String moduleId) {
    if (activeModules.contains(moduleId)) {
      activeModules.remove(moduleId);
      return "Student successfully signed out of module with Id " + moduleId;
    } else {
      return "Student did not sign up in module with id " + moduleId;
    }
  }

  public String passedModule(String moduleId, double grade) {
    if (activeModules.contains(moduleId) && !passedModules.containsKey(moduleId) && grade <= 4) {

      activeModules.remove(moduleId);
      passedModules.put(moduleId, grade);
      return "Student passed module with Id: " + moduleId;

    } else if (passedModules.containsKey(moduleId)) {
      return "Student already passed module with Id: " + moduleId;

    } else if (grade > 4) {
      return "Student needs a Grader better than 4.1 in order to pass";
    } else {
      return "Student cannot pass module with Id: " + moduleId + " because he didn't signed up for it.";

    }

  }

 /*  public String failedModule(String moduleId, double grade) {
    int trys = 0;
    if (!failedModules.containsKey(moduleId)) {
      failedModules.put(moduleId, new ArrayList<>());
    }
    if (activeModules.contains(moduleId)) {

      for (String i : failedModules.keySet()) {
        if (i.equals(moduleId)) {
          trys++;
          if (trys >= 3) {
            activeModules.remove(moduleId);
            failedModules.get(moduleId).add(grade);
            studentCourseId = null;
            return "Student failed module with Id: " + moduleId + " for the third time and will now be exmatriculated";
          }
        }
      }

      activeModules.remove(moduleId);
      failedModules.get(moduleId).add(grade);
      return "Student failed Module with Id: " + moduleId + " for the " + trys + ". time";
    } else if (passedModules.containsKey(moduleId)) {
      return "Student cannot fail module with Id: " + moduleId + " because he already passed it.";
    } else {
      return "Student cannot fail module with Id: " + moduleId + " because he didn't signed up for it.";
    }
  } */


  public String failedModule(String moduleId, double grade) {
    if (!failedModules.containsKey(moduleId)) {
        failedModules.put(moduleId, new ArrayList<>());
    }
    
    if (activeModules.contains(moduleId)) {
        int trys = failedModules.get(moduleId).size();
        
        if (trys >= 2) { // Da 0-basierte Zählung, 2 entspricht dem 3. Fehlversuch
            activeModules.remove(moduleId);
            failedModules.get(moduleId).add(grade);
            studentCourseId = null;
            return "Student failed module with Id: " + moduleId + " for the third time and will now be exmatriculated";
        } else {
            failedModules.get(moduleId).add(grade);
            return "Student failed Module with Id: " + moduleId + " for the " + (trys + 1) + ". time";
        }
    } else if (passedModules.containsKey(moduleId)) {
        return "Student cannot fail module with Id: " + moduleId + " because he already passed it.";
    } else {
        return "Student cannot fail module with Id: " + moduleId + " because he didn't signed up for it.";
    }
}

  

}
