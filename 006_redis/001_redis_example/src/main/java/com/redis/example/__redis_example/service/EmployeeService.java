package com.redis.example.__redis_example.service;

import java.security.PublicKey;
import java.util.List;

import com.redis.example.__redis_example.entity.EmployeesEntity;

public interface EmployeeService {
	
	public EmployeesEntity newEmployee(EmployeesEntity employeesEntity);
	
	public EmployeesEntity getEmployeeById(Long id);
	
	public EmployeesEntity getEmployeeByEmail(String email);
	
	public EmployeesEntity getEmployeeByName(String name);
	
	public List<EmployeesEntity> getAll();

}
