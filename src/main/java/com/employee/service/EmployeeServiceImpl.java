package com.employee.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.employee.dao.EmployeeRepository;
import com.employee.entities.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;
  
  //@Autowired
  //private JavaMailSender javaMailSender;
 
  @Override
  public Employee addEmployee(Employee employee) {
    // Generate UUID for Employee ID and save to the database
    employee.setId(UUID.randomUUID());
    Employee addedEmployee = null;
    
    try {
      addedEmployee = employeeRepository.save(employee);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
   
    // Send Email to Level 1 Manager on New Employee Addition
    // sendEmailToManager(addedEmployee);

    return addedEmployee;
  }
  
  /*
  private void sendEmailToManager(Employee employee) {
    if (employee.getReportsTo() != null) {
      // Employee's direct manager is at Level 1
      String subject = "New Employee Addition";
      String message = String.format(
        "%s will now work under you. Mobile number is %s and email is %s",
        employee.getEmployeeName(), employee.getPhoneNumber(), employee.getEmail()
    );

      SimpleMailMessage mailMessage = new SimpleMailMessage();
      Employee manager = getEmployeeById(UUID.fromString(employee.getReportsTo()));
      mailMessage.setTo(manager.getEmail());
      mailMessage.setSubject(subject);
      mailMessage.setText(message);

      // Use the email account created for this purpose to send the email
      mailMessage.setFrom("pankajkdevelopment@gmail.com");

      javaMailSender.send(mailMessage);
    }
  }
  */
  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public void deleteEmployeeById(UUID id) {
    employeeRepository.deleteById(id);
  }

  @Override
  public Employee updateEmployeeById(UUID id, Employee employee) {
    Employee existingEmployee = employeeRepository.findById(id).get();
    
    if (existingEmployee == null) {
      return null;
    }

    existingEmployee.setEmployeeName(employee.getEmployeeName());
    existingEmployee.setPhoneNumber(employee.getPhoneNumber());
    existingEmployee.setEmail(employee.getEmail());
    existingEmployee.setReportsTo(employee.getReportsTo());
    existingEmployee.setProfileImage(employee.getProfileImage());

    return employeeRepository.save(existingEmployee);
  }
  
  @Override
  public Employee getNthLevelManager(UUID employeeId, int level) {
    return findNthLevelManager(employeeId, level);
  }

  private Employee findNthLevelManager(UUID employeeId, int level) {
    Employee employee = employeeRepository.findById(employeeId).get();
    
    if (employee == null) {
      return null;
    }

    if (level <= 0) {
      return null;
    }
    int count = 1;
    System.out.println(employee);
    for(; count<=level; count++){
      System.out.println(count);
      UUID uuid =  null;
      if (employee.getReportsTo() == null) {
        return employee;
      }else {
        uuid = UUID.fromString(employee.getReportsTo());
        
      }
      
      employee =  getEmployeeById(uuid);
      System.out.println(employee);
      if(count == level) return employee;
    }
    
    return null; 
  }
  
  public Employee getEmployeeById(UUID id) {
    return employeeRepository.findById(id).get();
  }
/*
  private Employee findNthLevelManager(Employee employee, int level) {
    if (level <= 0) {
      return employee;
    }
    if (employee.getReportsTo() == null) {
      return null;
    }
    return findNthLevelManager(getIdFromReportsTo(employee.getReportsTo()) , level - 1);
  }
*/
  @Override
  public List<Employee> getAllEmployees(int page, int pageSize, String sortBy) {
    Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
    PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
    Page<Employee> employeePage = employeeRepository.findAll(pageRequest);
    return employeePage.getContent();
  }

     
}
