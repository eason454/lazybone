package com.ai.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ai.domain.CourseUserRank;

public interface ITraineeService {
	
	public Page<CourseUserRank> getRank(String userId, String courseId ,Pageable pageable) throws Exception;
}
