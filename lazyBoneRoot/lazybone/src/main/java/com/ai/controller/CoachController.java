package com.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.interfaces.ICoachService;

@RestController
public class CoachController {
	
	@Autowired
	ICoachService coachService;
	
	@PostMapping(path="/queryMyStudents")
	public Page<String> queryMyStudents(@RequestParam String courseId, Pageable pageable) throws Exception{
		return coachService.getMyStudents(courseId,pageable);
	}

}
