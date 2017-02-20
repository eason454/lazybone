package com.ai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.repository.UserCourseRepository;
import com.ai.service.interfaces.ITraineeService;

@Service
public class TraineeServiceImpl implements ITraineeService {
	
	@Autowired
	UserCourseRepository userCourseRepository;
	
	@Override
	public List<String> getRank(String userId, String courseId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
