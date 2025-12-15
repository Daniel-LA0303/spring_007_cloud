package com.mx.mcsv.user.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.mcsv.user.models.User;
import com.mx.mcsv.user.services.UserService;

@RestController
public class UserController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private Environment env;

	@GetMapping("/crash")
	public void crash() {
		((ConfigurableApplicationContext)context).close();
	}

	//get the token from the auth service
	@GetMapping("/authorized")
	public Map<String, Object> authorized(@RequestParam(name = "code") String code) {
		return Collections.singletonMap("code", code);
	}

	@GetMapping("/login")
	public ResponseEntity<?> loginByEmail(@RequestParam(name = "email") String email) {
		Optional<User> user = userService.findByEmail(email);

		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		return ResponseEntity.notFound().build();
	}

	private static ResponseEntity<Map<String, String>> valid(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "the filed " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<User> userOptional = userService.byId(id);
		if (userOptional.isPresent()) {
			userService.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detail(@PathVariable Long id) {

		Optional<User> user = userService.byId(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/users-course")
	public ResponseEntity<?> getUsersByCourse(@RequestParam List<Long> ids) {
		return ResponseEntity.ok(userService.usersByCourse(ids));
	}

	@GetMapping
	public Map<String, Object> list() {

		Map<String, Object> response = new HashMap<>();
		response.put("users", userService.list());
		response.put("podInfo", env.getProperty("MY_POD_NAME") + " - " + env.getProperty("MY_POD_IP"));
		return response;
	}

	@PostMapping
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {

		if (result.hasErrors()) {
			return valid(result);
		}

		if (!user.getEmail().isEmpty() && userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body(Collections.singletonMap("email", "email already exists"));
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return valid(result);
		}

		Optional<User> userOptional = userService.byId(id);
		if (userOptional.isPresent()) {
			User userDb = userOptional.get();
			if (!user.getEmail().equalsIgnoreCase(userDb.getEmail())
					&& userService.findByEmail(user.getEmail()).isPresent()) {
				return ResponseEntity.badRequest().body(Collections.singletonMap("email", "email already exists"));
			}
			userDb.setName(user.getName());
			userDb.setEmail(user.getEmail());
			userDb.setPassword(passwordEncoder.encode(user.getPassword()));
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDb));
		}

		return ResponseEntity.notFound().build();
	}
}
