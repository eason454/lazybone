package com.ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.domain.CourseUserRank;
import com.ai.service.interfaces.ITraineeService;

@RestController
public class TraineeController {
	
	@Autowired
	ITraineeService traineeSerivce;
	
	@GetMapping(path="/getRank/{courseId}/{userId}")
	public List<CourseUserRank> getRank(String userId, String courseId) throws Exception{
		return traineeSerivce.getRank(userId, courseId);
	}
	

}
