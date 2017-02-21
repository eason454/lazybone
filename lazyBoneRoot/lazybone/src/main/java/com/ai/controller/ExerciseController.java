package com.ai.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(path = "/recordExercise/{courseId}/{fitActionId}/{userId}")
	@Async
	public void recordExercise(@PathVariable("courseId") String courseId, @PathVariable("userId") String userId,
			@PathVariable("fitActionId") String fitActionId) throws Exception {
		exerciseService.recordExercise(courseId, userId, fitActionId);
	}
	
	@PostMapping(path = "/queryMyTodayExerciseInfo/{userId}/{date}")
	public String queryCurrentExerciseInfo(@PathVariable("userId") String userId ,@PathVariable("date") Date date){
		return null;
	}
	
	@PostMapping(path = "/refreshUserCourseProcess")
	public boolean refreshUserCourseProcess(){
		exerciseService.refreshUserCourseProcess();
		return true;
	}
}
