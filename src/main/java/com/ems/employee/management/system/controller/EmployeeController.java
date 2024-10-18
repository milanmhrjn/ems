package com.ems.employee.management.system.controller;

import com.ems.employee.management.system.dto.EmployeeDTO;
import com.ems.employee.management.system.entity.Employee;
import com.ems.employee.management.system.repo.EmployeeRepo;
import com.ems.employee.management.system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create-employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }

    @GetMapping("/get-employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees,HttpStatus.FOUND);
    }

    @GetMapping("/get-employee/{id}")
//    public ResponseEntity<Object> getEmployeeById(@PathVariable int id){
//        Employee employee = employeeRepo.findById(id).orElse(null);
//        if(employee!=null) {
//            return new ResponseEntity<>(employee, HttpStatus.FOUND);
//        }
//        else{
//            String message = "Employee id " +id + " is not available.";
//            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
//        }
//    }

    public ResponseEntity<Object> getEmployeeById(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if(employee!=null){
            return new ResponseEntity<>(employee,HttpStatus.FOUND);
        }
        else{
            String message = "Employee id " +id + " is not available.";
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable int id){
       Employee employee =  employeeRepo.findById(id).orElse(null);
       if(employee!=null){
           employeeRepo.deleteById(id);
           return new ResponseEntity<>("Employee with id "+id+" deleted successfully",HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>("Employee id "+id+ "is not available.", HttpStatus.NOT_FOUND);
       }
    }


    @PutMapping("/update-employee/{id}")
//    public ResponseEntity<Object> updateEmployeeById(@PathVariable int id, @RequestBody Employee employee){
//        Employee existingEmployee = employeeRepo.findById(id).orElse(null);
//        if(existingEmployee!=null){
//            Employee updateEmployee = existingEmployee;
//            updateEmployee.setFirstName(employee.getFirstName());
//            updateEmployee.setLastName(employee.getLastName());
//            updateEmployee.setAddress(employee.getAddress());
//            updateEmployee.setEmployeeType(employee.getEmployeeType());
//            employeeRepo.save(updateEmployee);
//            return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
//        }
//        else{
//            String message = "Employee id "+id+ "is not available.";
//            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
//        }
//    }

    public ResponseEntity<Object> updateEmployeeById(@PathVariable int id, @RequestBody Employee employee){
        Employee existingEmployee = employeeService.updateEmployee(id);
        if(existingEmployee!=null){
            Employee updateEmployee = existingEmployee;
            updateEmployee.setFirstName(employee.getFirstName());
            updateEmployee.setLastName(employee.getLastName());
            updateEmployee.setAddress(employee.getAddress());
            updateEmployee.setEmployeeType(employee.getEmployeeType());
            employeeService.saveEmployee(updateEmployee);
            return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
        }
        else{
            String message = "Employee id " +id+ "is not available.";
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/terminate-employee/{id}")
    public ResponseEntity<Object> terminateEmployeeById(@PathVariable int id, @RequestBody Employee employee){
        Employee beforeTermination = employeeService.employeeTerminate(id);
        if(beforeTermination!=null){
            Employee employeeTermination = beforeTermination;
            employeeTermination.setTerminationDate(employee.getTerminationDate());
            employeeService.saveEmployee(employeeTermination);
            return new ResponseEntity<>(employeeTermination,HttpStatus.OK);
        }
        else{
            String message = "Employee id " +id+ "is not available.";
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
}
