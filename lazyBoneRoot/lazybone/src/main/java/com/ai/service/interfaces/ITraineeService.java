package com.ai.service.interfaces;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ai.domain.CourseUserRank;

public interface ITraineeService {

	Page<CourseUserRank> getRank(String userId, String courseId, Date startDate, Date endDate, Pageable pageable)
			throws Exception;
}
