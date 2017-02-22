package com.ai.domain;

import com.ai.util.consts.CommonConst;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eason on 2017/2/16.
 */
@Entity
@Table(name="user_exercise_log")
public class UserExerciseLog {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "log_id")
    private String logId;

    @Column(name = "user_id")
    private String userId;
    //用户课程订购实例
    @Column(name = "user_course_id")
    private String userCourseId;
    //课程
    @Column(name = "course_id")
    private String courseId;
    //课程项目
    @Column(name = "course_item_id")
    private String courseItemId;
    //锻炼次数
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
    @Enumerated(EnumType.ORDINAL)
    private CommonConst.State state;
    
    @Column(name = "exercise_type")
    private String exerciseType;

    //需要锻炼次数
    @Column(name = "require_times")
    private int requireTimes = 0 ;
    //锻炼进度
    private int process = 0;
    
    public UserExerciseLog() {

    }

    public void setState(CommonConst.State state) {
        this.state = state;
    }

//    public UserExerciseLog(String userId, UserCourse userCourse, int actualCount, Date createDate, Date updateDate, Date exerciseDate, CommonConst.State state, FitAction exerciseType) {
//
//        this.userId = userId;
//        this.userCourse = userCourse;
//        this.actualCount = actualCount;
//        this.createDate = createDate;
//        this.updateDate = updateDate;
//        this.exerciseDate = exerciseDate;
//        this.state = state;
//        this.exerciseType = exerciseType;
//    }

    
    public String getLogId() {
        return logId;
    }

    public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
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

    

    public int getRequireTimes() {
		return requireTimes;
	}

	public void setRequireTimes(int requireTimes) {
		this.requireTimes = requireTimes;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public CommonConst.State getState() {
		return state;
	}
	
	public String getUserCourseId() {
		return userCourseId;
	}

	public void setUserCourseId(String userCourseId) {
		this.userCourseId = userCourseId;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCourseItemId() {
		return courseItemId;
	}

	public void setCourseItemId(String courseItemId) {
		this.courseItemId = courseItemId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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
