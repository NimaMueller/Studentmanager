package com.studentmanager.studentmanager.module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    Module findByModuleId(long moduleId);

 /*    @Modifying
    @Transactional
    @Query("UPDATE Module m SET m.description = :description WHERE m.moduleId = :moduleId")
    void updateModulebyModuleById(@Param("moduleId") Integer moduleId, @Param("description") String description); */
}
