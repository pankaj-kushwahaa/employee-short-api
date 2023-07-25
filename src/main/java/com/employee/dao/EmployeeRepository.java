package com.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import com.employee.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
  public Employee findByReportsTo(String reportsTo);
}
