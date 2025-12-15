package com.mx.mcsv.course.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mx.mcsv.course.models.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

	@Modifying
	@Query("delete from CourseUser cu where cu.userId=?1")
	public void deleteCourseUserById(Long id);

}
