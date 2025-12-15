package com.mx.mcsv.user.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.mcsv.user.clients.CourseClientRest;
import com.mx.mcsv.user.models.User;
import com.mx.mcsv.user.repositories.UserRepository;
import com.mx.mcsv.user.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseClientRest courseClientRest;

	@Override
	@Transactional(readOnly = true)
	public Optional<User> byId(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
		courseClientRest.deleteUserCourseById(id); // we delete the user in the courses too
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.perEmail(email);
	}

	@Override
	@Transactional(readOnly = true) // this annotation is to indicate that this method is only for reading
	public List<User> list() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> usersByCourse(Iterable<Long> id) {
		return (List<User>) userRepository.findAllById(id);
	}
}
