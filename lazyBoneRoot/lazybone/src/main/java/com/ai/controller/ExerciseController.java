package com.ai.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping(path = "/recordExercise/{courseId}/{fitActionId}/{userId}")
	public void recordExercise(@PathVariable("courseId") String courseId, @PathVariable("userId") String userId,
			@PathVariable("fitActionId") String fitActionId) throws Exception {
		exerciseService.recordExercise(courseId, userId, fitActionId);
	}

	@RequestMapping(path = "/queryExerciseInfo/{userId}/{userCourseId}")
	public UserExerciseInfoPage queryExerciseInfo(@PathVariable("userId") String userId,
			@PathVariable("userCourseId") String userCourseId, Pageable pageable) throws Exception {
		UserExerciseInfoPage userExerciseInfoPage = new UserExerciseInfoPage();
		userExerciseInfoPage.setUserExerciseLogs(exerciseService.getUserCourseDetail(userId, userCourseId, pageable));
		userExerciseInfoPage.setUserCourse(userCourseService.findById(userCourseId));
		return userExerciseInfoPage;
	}
	
	@RequestMapping(path = "/queryExerciseInfo/{userId}/{userCourseId}/{date}")
	@ResponseBody
	public UserExerciseInfo queryExerciseInfo(@PathVariable("userId") String userId,
			@PathVariable("userCourseId") String userCourseId, @PathVariable("date") String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date searchDate = sdf.parse(date);

		List<UserExerciseLog> userExerciseLogs = exerciseService.queryUserExerciseInfo(userId, userCourseId,
				ConstUtils.getDateStartTime(searchDate), ConstUtils.getDateEndTime(searchDate));
		UserExerciseInfo userExerciseInfo = new UserExerciseInfo();

		userExerciseInfo.setUserExerciseLogs(userExerciseLogs);
		userExerciseInfo.setUserCourses(userCourseService.queryUserCourses(userId));
		return userExerciseInfo;
	}

	@RequestMapping(path = "/queryExerciseInfo/{userId}/{userCourseId}/{startDate}/{endDate}")
	@ResponseBody
	public UserExerciseInfo queryExerciseInfo(@PathVariable("userId") String userId,
			@PathVariable("userCourseId") String userCourseId, @PathVariable("startDate") String startDateStr,
			@PathVariable("endDate") String endDateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = sdf.parse(startDateStr);
		Date endDate = sdf.parse(endDateStr);

		List<UserExerciseLog> userExerciseLogs = exerciseService.queryUserExerciseInfo(userId, userCourseId,
				ConstUtils.getDateStartTime(startdate), ConstUtils.getDateEndTime(endDate));

		UserExerciseInfo userExerciseInfo = new UserExerciseInfo();
		userExerciseInfo.setUserExerciseLogs(userExerciseLogs);
		userExerciseInfo.setUserCourses(userCourseService.queryUserCourses(userId));
		return userExerciseInfo;
	}

	@RequestMapping(path = "/refreshUserCourseProcess")
	public boolean refreshUserCourseProcess() {
		exerciseService.refreshUserCourseProcess();
		return true;
	}

	public class UserExerciseInfo {
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

	public class UserExerciseInfoPage {
		Page<UserExerciseLog> userExerciseLogs;
		UserCourse userCourse;

		public Page<UserExerciseLog> getUserExerciseLogs() {
			return userExerciseLogs;
		}

		public void setUserExerciseLogs(Page<UserExerciseLog> userExerciseLogs) {
			this.userExerciseLogs = userExerciseLogs;
		}

		public UserCourse getUserCourse() {
			return userCourse;
		}

		public void setUserCourse(UserCourse userCourse) {
			this.userCourse = userCourse;
		}
	}
}
