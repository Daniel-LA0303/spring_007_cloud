package com.redis.example.__redis_example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.redis.example.__redis_example.entity.EmployeesEntity;
import com.redis.example.__redis_example.repository.EmployeeCacheRepository;
import com.redis.example.__redis_example.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
    private final EmployeeRepository employeeRepository;
    private final EmployeeCacheRepository cacheRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCacheRepository cacheRepository) {
        this.employeeRepository = employeeRepository;
        this.cacheRepository = cacheRepository;
    }

	@Override
	public EmployeesEntity getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public EmployeesEntity getEmployeeByEmail(String email) {
		return employeeRepository.getEmployeeByEmail(email);
	}

	@Override
	public EmployeesEntity getEmployeeByName(String name) {
		return employeeRepository.getEmployeeByName(name);
	}

    @Override
    public List<EmployeesEntity> getAll() {
        // Primero revisa en Redis
        List<EmployeesEntity> cached = cacheRepository.getAll();
        if (cached != null) {
            System.out.println("Retornando empleados desde Redis");
            return cached;
        }

        // Si no hay cache, va a la DB
        List<EmployeesEntity> employees = employeeRepository.findAll();
        cacheRepository.saveAll(employees); // Guardar en Redis
        System.out.println("Retornando empleados desde DB y guardando en Redis");
        return employees;
    }

	@Override
	public EmployeesEntity newEmployee(EmployeesEntity employeesEntity) {
		
		EmployeesEntity saved = employeeRepository.save(employeesEntity);
		
		// delete from redis
		cacheRepository.deleteGetAll();

        return saved;

		//return employeeRepository.save(employeesEntity);
	}
}
