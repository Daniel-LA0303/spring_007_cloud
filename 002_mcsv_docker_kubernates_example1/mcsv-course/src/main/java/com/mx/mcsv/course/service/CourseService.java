package com.mx.mcsv.course.service;

import java.util.List;
import java.util.Optional;

import com.mx.mcsv.course.models.dto.User;
import com.mx.mcsv.course.models.entity.Course;

public interface CourseService {

	// this methods are for the table user_course
	Optional<User> asignedUser(User user, Long id);

	Optional<Course> byId(Long id);

	// with this method we get one list of users by one course
	Optional<Course> byIdWithUsers(Long id);

	Optional<User> createUser(User user, Long id);

	void delete(Long id);

	Optional<User> deleteUser(User user, Long id);

	// when delete one user this user deletes in the courses too
	void deleteUserCourseById(Long id);

	// persintence methods
	List<Course> list();

	Course save(Course course);

}
