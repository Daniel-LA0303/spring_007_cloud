package com.mx.mcsv.course.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mx.mcsv.course.models.dto.User;

@FeignClient(name = "mcsv-users-container")
public interface UserClientRest {

	@PostMapping("/")
	User create(@RequestBody User user);

	// we communicate with the microservice mcsv-users to get the user by id (as
	// controller)
	@GetMapping("/{id}")
	User getUser(@PathVariable Long id);

	@GetMapping("users-course")
	List<User> getUsersByCourse(@RequestParam Iterable<Long> ids);
}
