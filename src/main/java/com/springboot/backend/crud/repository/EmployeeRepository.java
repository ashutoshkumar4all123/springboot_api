package com.springboot.backend.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.crud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
