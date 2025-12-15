package com.redis.example.__redis_example.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "salary")
	private BigDecimal salary;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public EmployeesEntity() {
	}

	public EmployeesEntity(Long employeeId, String name, String email, BigDecimal salary, String bio, Boolean active,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.salary = salary;
		this.bio = bio;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public String getBio() {
		return bio;
	}

	public Boolean getActive() {
		return active;
	}


	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



}
