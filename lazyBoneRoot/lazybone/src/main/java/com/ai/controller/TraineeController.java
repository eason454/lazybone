package com.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.interfaces.ITraineeService;

@RestController
public class TraineeController {
	
	@Autowired
	ITraineeService traineeSerivce;
	
	

}
