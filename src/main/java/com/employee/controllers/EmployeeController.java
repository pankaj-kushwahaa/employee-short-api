package com.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import com.employee.service.EmployeeService;
import java.util.List;
import java.util.UUID;
import com.employee.entities.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  // Add Employee to a Database
  @PostMapping
  public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
    Employee addedEmployee = employeeService.addEmployee(employee);
    ResponseEntity<?> responseEntity = null;
    if (addedEmployee == null) {
      responseEntity = new ResponseEntity<String>("{\"msg\": \"Employee with this Email-Id already exists.\"}", HttpStatus.BAD_REQUEST);
      return responseEntity;
    }
    return ResponseEntity.ok(addedEmployee);
  }

  // Get All Employees
  @GetMapping
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
  }

  // Delete Employee by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable UUID id) {
    employeeService.deleteEmployeeById(id);
    String msg = "{\"msg\": \"EmpId is not valid or Level is not valid\"}";
    return new ResponseEntity<String>(msg, HttpStatus.OK);
  }

  // Update Employee by ID
  @PutMapping("/{id}")
  public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
    Employee updatedEmployee = employeeService.updateEmployeeById(id, employee);
    ResponseEntity<?> responseEntity = null;
    if (updatedEmployee == null) {
      responseEntity = new ResponseEntity<String>("{\"msg\": \"Employee with thid Id does not exist.\"}", HttpStatus.NOT_FOUND);
      return responseEntity;
    }
    
    return ResponseEntity.ok(updatedEmployee);
  } 
  
  @GetMapping("/employees/{employeeId}/manager/{level}")
  public ResponseEntity<?> getNthLevelManager(@PathVariable UUID employeeId, @PathVariable int level) {

    Employee manager = employeeService.getNthLevelManager(employeeId, level);
    ResponseEntity<?> responseEntity = null;
    if (manager == null) {
      responseEntity = new ResponseEntity<String>("{\"msg\": \"EmpId is not valid or Level is not valid\"}", HttpStatus.NOT_FOUND);
      return responseEntity;
    } else {
      return ResponseEntity.ok(manager);
    }
  }
  
}

