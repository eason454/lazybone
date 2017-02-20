package com.ai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.domain.UserCourse;
import com.ai.repository.UserCourseRepository;
import com.ai.service.interfaces.ICoachService;
import com.ai.util.consts.CommonConst;

@Service
public class CoachServiceImpl implements ICoachService{
	
	@Autowired
	UserCourseRepository userCourseRepository;

	@Override
	public List<String> getMyStudents(String courseId) throws Exception {
		List<String> users = new ArrayList<String>();
		List<UserCourse> userCourses =  userCourseRepository.findByCourseIdAndState(courseId, CommonConst.State.valid);
		userCourses.forEach(userCourse -> users.add(userCourse.getUserId()));
		return users;
	}
	
}