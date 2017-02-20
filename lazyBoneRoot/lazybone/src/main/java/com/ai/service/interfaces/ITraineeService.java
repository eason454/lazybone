package com.ai.service.interfaces;

import java.util.List;

import com.ai.domain.CourseUserRank;

public interface ITraineeService {
	
	public List<CourseUserRank> getRank(String userId, String courseId) throws Exception;
}
