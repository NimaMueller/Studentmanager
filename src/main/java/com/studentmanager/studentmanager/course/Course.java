package com.studentmanager.studentmanager.course;

import jakarta.persistence.Column;
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
    @Column(name = "course_id")
    private int courseId;
    
    @Column(name = "description")
    private String description;

    @Column(name= "degree")
    private String degree;
    
  //  private List<Module> moduleList;
}

/* enum degree {
    BachelorOfSciences, MasterOfSciences
} */
