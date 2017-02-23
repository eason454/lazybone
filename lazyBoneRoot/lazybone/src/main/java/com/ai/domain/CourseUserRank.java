package com.ai.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CourseUserRank {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	String userRankId;
	
	@Column(name = "course_id")
	String courseId;
	
	@Column(name = "user_course_id")
	String userCourseId;
	
	@Column(name="user_id")
	String userId;
	
	long rank;
	
	long process;
	
	@Column(name = "rank_date")
//    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date rankDate;

	public String getUserRankId() {
		return userRankId;
	}

	public void setUserRankId(String userRankId) {
		this.userRankId = userRankId;
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

	public long getProcess() {
		return process;
	}

	public void setProcess(long process) {
		this.process = process;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Date getRankDate() {
		return rankDate;
	}

	public void setRankDate(Date rankDate) {
		this.rankDate = rankDate;
	}

	public String getUserCourseId() {
		return userCourseId;
	}

	public void setUserCourseId(String userCourseId) {
		this.userCourseId = userCourseId;
	}
	
}
