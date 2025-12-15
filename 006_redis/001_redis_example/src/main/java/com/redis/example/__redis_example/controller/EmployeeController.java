package com.redis.example.__redis_example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.example.__redis_example.entity.EmployeesEntity;
import com.redis.example.__redis_example.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController{
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping()
	public ResponseEntity<List<EmployeesEntity>> getAll() {
	    List<EmployeesEntity> employees = employeeService.getAll();
	    return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeesEntity> getEmployeeById(@PathVariable Long id) {
	    EmployeesEntity employee = employeeService.getEmployeeById(id);
	    return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@GetMapping("/by-email/{email}")
	public ResponseEntity<EmployeesEntity> getEmployeeByEmail(@PathVariable String email) {
	    EmployeesEntity employee = employeeService.getEmployeeByEmail(email);
	    return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@GetMapping("/by-name/{name}")
	public ResponseEntity<EmployeesEntity> getEmployeeByName(@PathVariable String name) {
	    EmployeesEntity employee = employeeService.getEmployeeByName(name);
	    return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<EmployeesEntity> newEmployee(@RequestBody EmployeesEntity employeesEntity) {
	    EmployeesEntity employee = employeeService.newEmployee(employeesEntity);
	    return new ResponseEntity<>(employee, HttpStatus.OK);
	}

}
