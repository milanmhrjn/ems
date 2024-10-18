package com.ems.employee.management.system.service;

import com.ems.employee.management.system.entity.Employee;
import com.ems.employee.management.system.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;


    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee getEmployee(int id){
        return employeeRepo.findById(id).orElse(null);
    }

    public void deleteEmployee(int id){
        employeeRepo.delete(getEmployee(id));
    }

    public Employee updateEmployee(int id){
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee employeeTerminate(int id){
        return employeeRepo.findById(id).orElse(null);
    }
}
