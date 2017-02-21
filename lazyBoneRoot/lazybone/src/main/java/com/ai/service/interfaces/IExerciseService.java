package com.ai.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ai.domain.CourseItem;
import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;

public interface IExerciseService {

	public Page<UserExerciseLog> getCourseDetail(String userId, String courseId, Pageable pageable) throws Exception;
	
	public void recordExercise(String courseId ,String userId ,String fitActionId) throws Exception;
	
	public UserExerciseLog createCourseDetail(UserCourse userCourse,CourseItem courseItem) throws Exception;
	
	public void refreshUserCourseProcess();
}
