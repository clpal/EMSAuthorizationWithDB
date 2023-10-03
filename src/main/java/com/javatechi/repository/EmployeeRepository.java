package com.javatechi.repository;

import com.javatechi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
