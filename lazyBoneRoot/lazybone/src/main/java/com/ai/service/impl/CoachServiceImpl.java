package com.ai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;
import com.ai.repository.CourseRepository;
import com.ai.repository.UserCourseRepository;
import com.ai.service.interfaces.ICoachService;
import com.ai.util.consts.CommonConst;

@Service
public class CoachServiceImpl implements ICoachService{
	
	@Autowired
	UserCourseRepository userCourseRepository;

	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public Page<String> getMyStudents(String courseId ,Pageable pageable) throws Exception {
		List<String> users = new ArrayList<String>();
		Course course = courseRepository.findOne(courseId);
		Page<UserCourse> userCourses =  userCourseRepository.findByCourseAndState(course, CommonConst.State.valid, pageable);
		
		userCourses.forEach(userCourse -> users.add(userCourse.getUserId()));
		
		Page<String> userPage = new PageImpl<String>(users);
		BeanUtils.copyProperties(userCourses, userPage);
		return userPage;
	}
	
}
