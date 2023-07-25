package com.employee.service;

import java.util.List;
import java.util.UUID;
import com.employee.entities.Employee;


public interface EmployeeService {
  Employee addEmployee(Employee employee);
  List<Employee> getAllEmployees();
  void deleteEmployeeById(UUID id);
  Employee updateEmployeeById(UUID id, Employee employee);
  Employee getNthLevelManager(UUID employeeId, int level);
  List<Employee> getAllEmployees(int page, int pageSize, String sortBy);
}

