package com.ai.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.service.interfaces.IExerciseService;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.ConstUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ExerciseController {

	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	IExerciseService exerciseService;
	
	@Autowired
	IUserCourseService userCourseService;
	

	@GetMapping(path = "/queryCourseDetail/{courseId}/{userId}")
	public Page<UserExerciseLog> queryCourseDetail(@PathVariable("userId") String userId,
			@PathVariable("courseId") String courseId, Pageable pageable) throws Exception {
		return exerciseService.getCourseDetail(userId, courseId, pageable);
	}

	@PostMapping(path = "/recordExercise/{courseId}/{fitActionId}/{userId}")
	@Async
	public void recordExercise(@PathVariable("courseId") String courseId, @PathVariable("userId") String userId,
			@PathVariable("fitActionId") String fitActionId) throws Exception {
		exerciseService.recordExercise(courseId, userId, fitActionId);
	}

	@PostMapping(path = "/queryMyTodayExerciseInfo/{userId}/{date}")
	@ResponseBody
	public UserExerciseInfo queryUserExerciseInfoByDay(@PathVariable("userId") String userId, @PathVariable("date") String date)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date searchDate = sdf.parse(date);

		List<UserExerciseLog> userExerciseLogs = exerciseService.queryUserExerciseInfo(userId,
				ConstUtils.getDateStartTime(searchDate), ConstUtils.getDateEndTime(searchDate));
		UserExerciseInfo userExerciseInfo = new UserExerciseInfo();
		
		userExerciseInfo.setUserExerciseLogs(userExerciseLogs);
		userExerciseInfo.setUserCourses(userCourseService.queryUserCourses(userId));
		return userExerciseInfo;
	}

	@PostMapping(path = "/refreshUserCourseProcess")
	public boolean refreshUserCourseProcess() {
		exerciseService.refreshUserCourseProcess();
		return true;
	}
	
	public class UserExerciseInfo{
		List<UserExerciseLog> userExerciseLogs;
		List<UserCourse> userCourses;
		
		public List<UserExerciseLog> getUserExerciseLogs() {
			return userExerciseLogs;
		}
		
		public void setUserExerciseLogs(List<UserExerciseLog> userExerciseLogs) {
			this.userExerciseLogs = userExerciseLogs;
		}

		public List<UserCourse> getUserCourses() {
			return userCourses;
		}

		public void setUserCourses(List<UserCourse> userCourses) {
			this.userCourses = userCourses;
		}
	}
}
