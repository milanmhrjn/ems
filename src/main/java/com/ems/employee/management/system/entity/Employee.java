package com.ems.employee.management.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private int id;
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="address")
    private String address;

    @ManyToOne
    @JoinColumn(name="employee_role_id")
    private EmployeeType employeeType;
    
    @Column(name="joined_date", updatable = false)
    @CreatedDate
    @CreationTimestamp
    private Date joinedDate;

    @Column(name="termination_date")
    private Date terminationDate;

    public Employee(int id, String firstName, String lastName, String address, EmployeeType employeeType, Date joinedDate,Date terminationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.employeeType = employeeType;
        this.joinedDate = joinedDate;
        this.terminationDate = terminationDate;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }
}

@Entity
@Table(name="employee role")
class EmployeeType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_role_id")
    private int id;
    @Column(name="employee_role")
    private String empRole;

    public EmployeeType(int id, String empRole) {
        this.id = id;
        this.empRole = empRole;
    }

    public EmployeeType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }
}

