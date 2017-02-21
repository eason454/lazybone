package com.ai.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class CourseUserRank {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	String userRankId;
	
	@Column(name = "course_id")
	String courseId;
	
	@Column(name="user_id")
	String userId;
	
	long rank;
	
	long count;
	
	long process;

	public String getUserRankId() {
		return userRankId;
	}

	public void setUserRankId(String userRankId) {
		this.userRankId = userRankId;
	}

	public String getCourse() {
		return courseId;
	}

	public void setCourse(String courseId) {
		this.courseId = courseId;
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getProcess() {
		return process;
	}

	public void setProcess(long process) {
		this.process = process;
	}
	
	
}
