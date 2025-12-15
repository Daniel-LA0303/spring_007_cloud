package com.redis.example.__redis_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redis.example.__redis_example.entity.EmployeesEntity;

public interface EmployeeRepository extends JpaRepository<EmployeesEntity, Long>{
	
	public EmployeesEntity getEmployeeByEmail(String email);
	
	public EmployeesEntity getEmployeeByName(String name);

}
