package com.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ai.pojo.CourseExerciseDetail;
import com.ai.service.interfaces.IExerciseService;

@RestController
public class ExerciseController {
	
	@Autowired
	IExerciseService exerciseService;
	
	
	@GetMapping(path="/queryCourseDetail/{courseId}/{userId}")
	public CourseExerciseDetail  queryCourseDetail(@PathVariable("userId") String userId, @PathVariable("courseId") String courseId ) throws Exception{
		return exerciseService.getCourseDetail(userId, courseId);
	}
	
	@GetMapping(path="/createCourseDetail/{courseId}/{userId}")
	public void createCourseDetail(@PathVariable("userId") String userId, @PathVariable("courseId") String courseId ) throws Exception{
		exerciseService.createCourseDetail(userId, courseId);
	}
}
