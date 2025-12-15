package com.mx.mcsv.course.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.mx.mcsv.course.models.dto.User;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "course_id")
	private List<CourseUser> courseUsers;

	@Transient
	private List<User> users;

	public Course() {
		courseUsers = new ArrayList<>();
		users = new ArrayList<>();
	}

	// ------------------------------------------------------------
	// this method add a user to the course
	public void addUser(CourseUser courseUser) {
		courseUsers.add(courseUser);
	}

	public List<CourseUser> getCourseUsers() {
		return courseUsers;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<User> getUsers() {
		return users;
	}

	// this method remove a user from the course
	public void removeUser(CourseUser courseUser) {
		courseUsers.remove(courseUser);
	}

	public void setCourseUsers(List<CourseUser> courseUsers) {
		this.courseUsers = courseUsers;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
