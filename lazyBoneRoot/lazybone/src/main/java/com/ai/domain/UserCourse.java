package com.ai.domain;

import com.ai.util.consts.CommonConst;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonProperty(value ="courseTemplateId",required = true)
    private Course course;
    @Column(name = "user_id")
    private String userId;
    @OneToMany(mappedBy = "userCourse",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserExerciseLog> userExerciseLogs=new ArrayList<>();

    public List<UserExerciseLog> getUserExerciseLogs() {
        return userExerciseLogs;
    }

    public void setUserExerciseLogs(List<UserExerciseLog> userExerciseLogs) {
        this.userExerciseLogs = userExerciseLogs;
    }
    public void addUserExerciseLog(UserExerciseLog userExerciseLog){
        userExerciseLogs.add(userExerciseLog);
        userExerciseLog.setUserCourse(this);
    }
    public void removeUserExerciseLog(UserExerciseLog userExerciseLog){
        userExerciseLogs.remove(userExerciseLog);
        userExerciseLog.setUserCourse(null);
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

    public UserCourse(Date createDate, CommonConst.State state, Date updateDate, Date endDate, Course course, String userId, List<UserExerciseLog> userExerciseLogs) {
        this.createDate = createDate;
        this.state = state;
        this.updateDate = updateDate;
        this.endDate = endDate;
        this.course = course;
        this.userId = userId;
        this.userExerciseLogs = userExerciseLogs;
    }
}
