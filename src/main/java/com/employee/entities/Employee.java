package com.employee.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String employeeName;
  private String phoneNumber;
  @Column(unique = true)
  private String email;
  private String reportsTo;
  private String profileImage;
  
  public Employee() {
    super();
  }

  public Employee(UUID id, String employeeName, String phoneNumber, String email, String reportsTo,
      String profileImage) {
    super();
    this.id = id;
    this.employeeName = employeeName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.reportsTo = reportsTo;
    this.profileImage = profileImage;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getReportsTo() {
    return reportsTo;
  }

  public void setReportsTo(String reportsTo) {
    this.reportsTo = reportsTo;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", employeeName=" + employeeName + ", phoneNumber=" + phoneNumber + ", email=" + email
        + ", reportsTo=" + reportsTo + ", profileImage=" + profileImage + "]";
  }

}
