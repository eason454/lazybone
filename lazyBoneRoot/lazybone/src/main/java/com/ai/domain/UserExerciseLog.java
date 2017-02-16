package com.ai.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eason on 2017/2/16.
 */
public class UserExerciseLog {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "log_id")
    private String logId;

    @Column(name = "user_id")
    private String userId;
    @ManyToOne
    private UserCourse userCourse;
    @Column(name = "actual_count")
    private int actualCount;
    @Column(name = "create_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "exercise_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exerciseDate;
    private int state;

    public UserExerciseLog(String userId, UserCourse userCourse, int actualCount, Date createDate, Date updateDate, Date exerciseDate, int state) {
        this.userId = userId;
        this.userCourse = userCourse;
        this.actualCount = actualCount;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.exerciseDate = exerciseDate;
        this.state = state;
    }

    public UserExerciseLog() {

    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserCourse getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(UserCourse userCourse) {
        this.userCourse = userCourse;
    }

    public int getActualCount() {
        return actualCount;
    }

    public void setActualCount(int actualCount) {
        this.actualCount = actualCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserExerciseLog that = (UserExerciseLog) o;

        return userId.equals(that.userId);

    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
