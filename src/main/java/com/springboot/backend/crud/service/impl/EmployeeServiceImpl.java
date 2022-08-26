package com.springboot.backend.crud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.backend.crud.exception.ResourceNotFoundException;
import com.springboot.backend.crud.model.Employee;
import com.springboot.backend.crud.repository.EmployeeRepository;
import com.springboot.backend.crud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee =employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "id", id);
//		}
		
		return employeeRepository.findById(id).orElseThrow(()  -> 
		       new ResourceNotFoundException("Employee", "id", id));
		
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// We need to check wethre the employee with given id is exist in database or not
		
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(()  -> 
		new ResourceNotFoundException("Employee", "id", id));
		
		existingEmployee.setFristName(employee.getFristName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail() );
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		// check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}
	
	
	
	
	

}
