package com.ai.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.domain.CourseUserRank;
import com.ai.service.interfaces.ITraineeService;
import com.ai.util.consts.ConstUtils;

@RestController
public class TraineeController {

	@Autowired
	ITraineeService traineeSerivce;

	@RequestMapping(path = "/getRank/{userId}/{courseId}/{date}")
	public Page<CourseUserRank> getRank(@PathVariable("userId") String userId,
			@PathVariable("courseId") String courseId, @PathVariable("date") String date, Pageable pageable)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date searchDate = sdf.parse(date);

		return traineeSerivce.getRank(userId, courseId,ConstUtils.getDateStartTime(searchDate), ConstUtils.getDateEndTime(searchDate),
				pageable);
	}
}
