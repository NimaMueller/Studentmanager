package com.studentmanager.studentmanager.module;

import java.util.Set;

import com.studentmanager.studentmanager.signUpForModule.SignUpFoModule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "modules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Module {

    @Id
    @Column(name = "module_id")
    private int moduleId;

    @Column(name = "description")
    private String description;

    @Column(name = "c_p")
    private int cP;
/* 
    @OneToMany(mappedBy = "module")
    Set<SignUpFoModule> signUp; */

}