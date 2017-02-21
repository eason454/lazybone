package com.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ai.domain.UserExerciseLog;
import com.ai.service.interfaces.IExerciseService;

@RestController
public class ExerciseController {

	@Autowired
	IExerciseService exerciseService;

	@GetMapping(path = "/queryCourseDetail/{courseId}/{userId}")
	public Page<UserExerciseLog> queryCourseDetail(@PathVariable("userId") String userId,
			@PathVariable("courseId") String courseId, Pageable pageable) throws Exception {
		return exerciseService.getCourseDetail(userId, courseId, pageable);
	}
	
}
