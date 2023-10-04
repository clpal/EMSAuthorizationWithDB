package com.javatechi.controller;

import com.javatechi.entity.Employee;
import com.javatechi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    public  static final String AUTHORITY_ROLE_HR="hasAuthority('ROLE_HR')";
    @Autowired
    EmployeeService service;
    @GetMapping("/welcome")
    public  String welcome(){
        return "Welcome to the javaTechie! . Your official credential already has been shared over email";
    }
    @PostMapping("/create")
   // @PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee onboardingNewEmployee(@RequestBody Employee employee){
        return  service.createNewEmployee(employee);
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_HR') or hasAuthority('ROLE_MANAGER') ")
    public List<Employee> getAll(){
        return  service.getEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Employee getEmployeeById(@PathVariable Integer id){
        return  service.getEmployee(id);
    }

    @PutMapping("/update")
  @PreAuthorize(AUTHORITY_ROLE_HR)
    public  Employee updateRoles(@RequestBody  Employee employee){
        return  service.changeRoleOfEmployee(employee);
    }
}
