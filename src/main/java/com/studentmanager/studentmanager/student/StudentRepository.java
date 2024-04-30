package com.studentmanager.studentmanager.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByMatriklNr(int matriklNr);

/*     @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.name = :name WHERE s.matriklNr = :matriklNr")
    void updateStudentbyMatriklNr(@Param("matriklNr") Integer matriklNr, @Param("name") String name); */

}
