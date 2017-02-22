package com.ai.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.ai.util.consts.CommonConst;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eason on 2017/2/16.
 */
@Entity
@Table(name = "user_course")
public class UserCourse {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "user_course_id")
    private String userCourseId;
    @Column(name = "create_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Enumerated(EnumType.ORDINAL)
    private CommonConst.State state;
    @Column(name = "update_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "end_date")
//    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonProperty(value ="courseTemplate",required = true)
    private Course course;
    @Column(name = "user_id")
    private String userId;
    private int process = 0;
    
    
    public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(String userCourseId) {
        this.userCourseId = userCourseId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CommonConst.State getState() {
        return state;
    }

    public void setState(CommonConst.State state) {
        this.state = state;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public UserCourse() {
    }

    public UserCourse(Date createDate, CommonConst.State state, Date updateDate, Date endDate, Course course, String userId) {
        this.createDate = createDate;
        this.state = state;
        this.updateDate = updateDate;
        this.endDate = endDate;
        this.course = course;
        this.userId = userId;
    }
}
