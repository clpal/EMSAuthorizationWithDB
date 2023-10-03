package com.javatechi.service;

import com.javatechi.entity.Employee;
import com.javatechi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    public Employee createNewEmployee(Employee employee){
        return  repository.save(employee);

    }
    public List<Employee>getEmployees(){
        return repository.findAll();
    }
    public Employee getEmployee(Integer id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("Employee Not found"+id));
    }
}
