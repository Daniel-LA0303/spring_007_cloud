package com.mx.mcsv.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mx.mcsv.user.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	@Query("select u from User u where u.email=?1")
	Optional<User> perEmail(String email);

}
