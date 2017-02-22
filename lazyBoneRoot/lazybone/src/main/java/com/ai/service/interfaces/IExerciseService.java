package com.ai.service.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ai.domain.CourseItem;
import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;

public interface IExerciseService {

	public Page<UserExerciseLog> getCourseDetail(String userId, String courseId, Pageable pageable) throws Exception;
	
	public void recordExercise(String courseId ,String userId ,String fitActionId) throws Exception;
	
	public UserExerciseLog createCourseDetail(UserCourse userCourse,CourseItem courseItem) throws Exception;
	
	public List<UserExerciseLog> queryUserExerciseInfo(String userId ,Date startDate, Date endDate) throws Exception;
	
	public void refreshUserCourseProcess();
	
	public List<UserExerciseLog> queryUserExerciseByUserCourseId(String userCourseId) throws Exception;
	
	public void updateUserExercise(List<UserExerciseLog> userExerciseLogs) throws Exception;
	
	public Page<UserExerciseLog> getUserCourseDetail(String userId, String userCourseId, Pageable pageable) throws Exception;

	List<UserExerciseLog> queryUserExerciseInfo(String userId, String userCourseId, Date startDate, Date endDate)
			throws Exception;
}
