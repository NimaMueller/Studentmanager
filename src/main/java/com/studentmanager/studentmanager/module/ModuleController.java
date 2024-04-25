package com.studentmanager.studentmanager.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/module")
public class ModuleController {

  @Autowired
  ModuleService moduleService;

  // Create a new Module and save it to the DB.
  @PostMapping("post")
  public String createModule(@RequestBody Module module) {
    return moduleService.createModule(module);
  }

  // Get a Module in the DB by it's module id.
  @GetMapping("{moduleId}")
  public Module getModule(@PathVariable int moduleId) {
    return moduleService.getModule(moduleId);
  }

  // Get every Module in the DB.
  @GetMapping("getAll")
  public List<Module> showAll() {
    return moduleService.getAllModules();
  }

/*   // Update a Module in the DB.
  @PutMapping("put")
  public Module updateStudent(@RequestBody Module module) {
    return moduleService.updateModule(module);
  } */

  // Delete a Module from the DB.
  @DeleteMapping("delete/{moduleId}")
  public String deleteModule(@PathVariable int moduleId) {
    return moduleService.deleteModule(moduleId);
  }

}
