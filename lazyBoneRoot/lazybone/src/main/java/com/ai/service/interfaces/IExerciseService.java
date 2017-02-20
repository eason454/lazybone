package com.ai.service.interfaces;

import com.ai.pojo.CourseExerciseDetail;

public interface IExerciseService {

	
	public void createCourseDetail(String userId, String courseId) throws Exception;
	
	public CourseExerciseDetail getCourseDetail(String userId, String courseId) throws Exception;
}
