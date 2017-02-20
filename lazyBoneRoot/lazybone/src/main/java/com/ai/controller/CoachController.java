package com.ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.interfaces.ICoachService;

@RestController
public class CoachController {
	
	@Autowired
	ICoachService coachService;
	
	@GetMapping(path="/queryMyStudents/{courseId}")
	public List<String> queryMyStudents(@PathVariable("courseId") String courseId) throws Exception{
		return coachService.getMyStudents(courseId);
	}

}
