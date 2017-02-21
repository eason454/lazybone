package com.ai.controller;

import com.ai.service.interfaces.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController {
	
	@Autowired
	ICoachService coachService;
	
	@PostMapping(path="/queryMyStudents")
	public Page<String> queryMyStudents(@RequestParam String courseId,@RequestParam(value = "page", defaultValue = "0") int page,
										@RequestParam(value = "size", defaultValue = "10") int size)throws Exception{
		Pageable pageable = new PageRequest(page, size, null);
		return coachService.getMyStudents(courseId,pageable);
	}

}
