package com.mx.mcsv.user.services;

import java.util.List;
import java.util.Optional;

import com.mx.mcsv.user.models.User;

public interface UserService {

	Optional<User> byId(Long id);

	void delete(Long id);

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	List<User> list();

	User save(User user);

	List<User> usersByCourse(Iterable<Long> id);

}
