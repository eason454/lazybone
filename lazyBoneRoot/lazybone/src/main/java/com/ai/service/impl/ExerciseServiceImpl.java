package com.ai.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.repository.UserCourseRepository;
import com.ai.repository.UserExerciseLogRepository;
import com.ai.service.interfaces.IExerciseService;
import com.ai.util.consts.CommonConst.State;

@Service
public class ExerciseServiceImpl implements IExerciseService {

	@Autowired
	UserExerciseLogRepository userExerciseLogRepository;
	@Autowired
	UserCourseRepository userCourseRepository;
	// 需要简单的缓存一下用户订购
	volatile static Map<String, UserCourse> userCourseCache = new WeakHashMap<String, UserCourse>();
	// 需要简单使用一下队列
	volatile static Queue<UserExerciseLog> exerciseQueue = new LinkedBlockingQueue<UserExerciseLog>();

	@Scheduled(cron="0 0 0 * * *")
	public void createCourseDetail() throws Exception {
		//查询用户课程
		List<UserCourse> userCourses =	userCourseRepository.findByState(State.valid);
		//生成锻炼记录
		userCourses.forEach( userCourse -> userCourse.getCourse().getCourseItems().forEach(courseItem -> {
			UserExerciseLog userExerciseLog =  new UserExerciseLog();
			Date date = new Date();
			userExerciseLog.setUserId(userCourse.getUserId());
			userExerciseLog.setUserCourse(userCourse);
			userExerciseLog.setState(State.valid);
			userExerciseLog.setCreateDate(date);
			userExerciseLog.setExerciseDate(date);
			userExerciseLog.setUpdateDate(date);
			userExerciseLog.setRequireTimes(courseItem.getRequireTimes());
			userExerciseLog.setExerciseType(courseItem.getExerciseType());
			userExerciseLogRepository.save(userExerciseLog);
		}));
	}
	
	@Override
	public Page<UserExerciseLog> getCourseDetail(String userId, String courseId, Pageable pageable) throws Exception {
		UserCourse userCourse = userCourseRepository.findByUserIdAndCourseIdAndState(userId, courseId, State.valid);
		return userExerciseLogRepository.findByUserCourseOrderByExerciseDateDesc(userCourse, pageable);
	}
	
}
