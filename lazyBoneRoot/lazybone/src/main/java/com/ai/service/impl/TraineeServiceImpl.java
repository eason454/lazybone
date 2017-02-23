package com.ai.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ai.domain.CourseUserRank;
import com.ai.repository.CourseRepository;
import com.ai.repository.CourseUserRankRepository;
import com.ai.repository.UserCourseRepository;
import com.ai.service.interfaces.ITraineeService;

@Service
public class TraineeServiceImpl implements ITraineeService {
	
	@Autowired
	UserCourseRepository userCourseRepository;
	@Autowired
	CourseUserRankRepository cousreUserRankRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public Page<CourseUserRank> getRank(String userId, String courseId, Date startDate, Date endDate, Pageable pageable) throws Exception {
		return cousreUserRankRepository.findByCourseIdBetweenRankDateOrderByRank(courseId,startDate,endDate,pageable);
	}
}
