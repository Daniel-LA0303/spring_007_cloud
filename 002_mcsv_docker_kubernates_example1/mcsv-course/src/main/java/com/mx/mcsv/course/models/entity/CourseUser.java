package com.mx.mcsv.course.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses_users")
public class CourseUser {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "course_id")
	private Long courseId;

	@Column(name = "user_id")
	private Long userId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { // this == instance of Course
			return true;
		}
		if (!(obj instanceof Course)) {
			return false;
		}
		CourseUser o = (CourseUser) obj;
		return this.userId != null && this.userId.equals(o.userId);
	}

	public Long getCourseId() {
		return courseId;
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
