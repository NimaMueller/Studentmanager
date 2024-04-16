package com.studentmanager.studentmanager.course;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Course {

    @Id
    private long courseId;
    private String description;
    private degree degree;
  //  private List<Module> moduleList;
}

enum degree {
    BachelorOfSciences, MasterOfSciences
}
