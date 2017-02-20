package com.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.interfaces.IExerciseService;

@RestController
public class ExerciseController {
	
	@Autowired
	IExerciseService exerciseService;
	
	
	@GetMapping(path="/queryCourseDetail/{courseId}/{userId}")
	public void queryCourseDetail(@PathVariable("userId") String userId, @PathVariable("courseId") String CourseId ){
		
	}
	
	@GetMapping(path="/getCourseDetail/{courseId}/{userId}")
	public void getCourseDetail(@PathVariable("userId") String userId, @PathVariable("courseId") String CourseId ){
		
	}
}
