package com.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.domain.CourseUserRank;
import com.ai.service.interfaces.ITraineeService;

@RestController
public class TraineeController {
	
	@Autowired
	ITraineeService traineeSerivce;
	
	@PostMapping(path="/getRank")
	public Page<CourseUserRank> getRank(String userId, String courseId,Pageable pageable) throws Exception{
		return traineeSerivce.getRank(userId, courseId, pageable);
	}
}
