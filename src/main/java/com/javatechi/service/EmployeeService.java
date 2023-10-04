package com.javatechi.service;

import com.javatechi.entity.Employee;
import com.javatechi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class EmployeeService {
    public  static final String DEFAULT_ROLE="ROLE_EMPLOYEE";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository repository;
    public Employee createNewEmployee(Employee employee){
        employee.setRoles(DEFAULT_ROLE);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return  repository.save(employee);

    }
    public List<Employee>getEmployees(){
        return repository.findAll();
    }
    public Employee getEmployee(Integer id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("Employee Not found"+id));
    }
    public Employee changeRoleOfEmployee(Employee employee){
      Employee existingEmployee=  getEmployee(employee.getId());
      existingEmployee.setRoles(employee.getRoles());
      return repository.save(existingEmployee);
    }
}
