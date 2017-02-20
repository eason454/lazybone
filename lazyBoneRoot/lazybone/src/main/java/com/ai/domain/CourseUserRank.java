package com.ai.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class CourseUserRank {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	String userRankId;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	Course course;
	
	@Column(name="user_id")
	String userId;
	
	long rank;

	public String getUserRankId() {
		return userRankId;
	}

	public void setUserRankId(String userRankId) {
		this.userRankId = userRankId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}
	
	
}
