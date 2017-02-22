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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ai.domain.Course;
import com.ai.domain.CourseItem;
import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.repository.CourseRepository;
import com.ai.repository.UserCourseRepository;
import com.ai.repository.UserExerciseLogRepository;
import com.ai.service.interfaces.IExerciseService;
import com.ai.util.consts.CommonConst.State;
import com.ai.util.consts.ConstUtils;

@Service
public class ExerciseServiceImpl implements IExerciseService {

	@Autowired
	UserExerciseLogRepository userExerciseLogRepository;
	@Autowired
	UserCourseRepository userCourseRepository;
	@Autowired
	CourseRepository courseRepository;

	// 需要简单的缓存一下用户订购
	volatile static Map<String, UserExerciseLog> userExerciseCache = new WeakHashMap<String, UserExerciseLog>();
	// 需要简单使用一下队列
	volatile static Queue<UserExerciseLog> exerciseQueue = new LinkedBlockingQueue<UserExerciseLog>();

	@Scheduled(cron = "0 0 0 * * *")
	public void createCourseDetail() throws Exception {
		// 查询用户课程
		List<UserCourse> userCourses = userCourseRepository.findByState(State.valid);
		// 生成锻炼记录
		userCourses.forEach(userCourse -> userCourse.getCourse().getCourseItems().forEach(courseItem -> {
			try {
				createCourseDetail(userCourse, courseItem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
	}

	public UserExerciseLog createCourseDetail(UserCourse userCourse, CourseItem courseItem) throws Exception {
		UserExerciseLog userExerciseLog = new UserExerciseLog();
		Date date = new Date();
		userExerciseLog.setUserId(userCourse.getUserId());
		userExerciseLog.setUserCourseId(userCourse.getUserCourseId());
		userExerciseLog.setCourseId(courseItem.getCourse().getId());
		userExerciseLog.setCourseItemId(courseItem.getCourseItemId());
		userExerciseLog.setState(State.valid);
		userExerciseLog.setCreateDate(date);
		userExerciseLog.setExerciseDate(new Date(ConstUtils.getTodayStartTime()));
		userExerciseLog.setUpdateDate(date);
		userExerciseLog.setRequireTimes(courseItem.getRequireTimes());
		userExerciseLog.setExerciseType(courseItem.getExerciseType().getId());
		userExerciseLogRepository.save(userExerciseLog);
		return userExerciseLog;
	}

	@Override
	public Page<UserExerciseLog> getCourseDetail(String userId, String courseId, Pageable pageable) throws Exception {
		UserCourse userCourse = userCourseRepository.findByUserIdAndCourseIdAndState(userId, courseId, State.valid);
		return userExerciseLogRepository.findByUserCourseIdOrderByExerciseDateDesc(userCourse.getUserCourseId(),
				pageable);
	}

	@Override
	public void recordExercise(String courseId, String userId, String exerciseType) throws Exception {
		UserExerciseLog userExerciseLog;
		// 按课程、用户、动作、日期查询运动记录
		userExerciseLog = userExerciseLogRepository.findByCourseIdAndUserIdAndExerciseTypeAndExerciseDate(courseId,
				userId, exerciseType, new Date(ConstUtils.getTodayStartTime()));
		// 如果还没生成那么生成一个运动记录
		if (userExerciseLog == null) {
			Course course = courseRepository.findOne(courseId);
			UserCourse userCourse = userCourseRepository.findByUserIdAndCourseAndState(userId, course, State.valid);
			for (CourseItem courseItem : userCourse.getCourse().getCourseItems()) {
				if (exerciseType.equals(courseItem.getExerciseType().getId())) {
					userExerciseLog = createCourseDetail(userCourse, courseItem);
				}
			}
		}

		userExerciseLog.setActualCount(userExerciseLog.getActualCount() + 1);
		// 计算运动进度，如果多余规定运动数那么是100%，低于要求运动数计算运动百分比
		int process = (int) (Math.floor(userExerciseLog.getActualCount() > userExerciseLog.getRequireTimes() ? 100
				: userExerciseLog.getActualCount()*100 / userExerciseLog.getRequireTimes()));

		userExerciseLog.setProcess(process);

		// 更新运动记录
		userExerciseLogRepository.save(userExerciseLog);
	}

	@Override
	@Scheduled(cron = "0 0 0 * * *")
	public void refreshUserCourseProcess(){
		List<UserCourse> userCourses = userCourseRepository.findByState(State.valid);
		// 循环每个有效课程
		for (UserCourse userCourse : userCourses) {
			userCourse.setProcess(computerUserCourseProcess(userCourse));
			userCourseRepository.save(userCourse);
		}
	}

	//计算当前课程进度
	private int computerUserCourseProcess(UserCourse userCourse){
		int resultProcess = 0;
		int courseDay = userCourse.getCourse().getCourseDays();
		int exeNumber = userCourse.getCourse().getCourseItems().size();
		int exeProcess = 0;
		for(CourseItem courseItem : userCourse.getCourse().getCourseItems()){
			List<UserExerciseLog> userExerciseLogs = userExerciseLogRepository.findByCourseItemIdAndUserCourseId(courseItem.getCourseItemId(),userCourse.getUserCourseId());
			for(UserExerciseLog userExerciseLog : userExerciseLogs){
				exeProcess += userExerciseLog.getProcess();
			}
		}
		//对进度取整
		resultProcess = (int) (Math.floor((exeProcess*100)/(exeNumber*courseDay*100)));
		
		return resultProcess;
	}

	@Override
	public List<UserExerciseLog> queryUserExerciseInfo(String userId, Date startDate, Date endDate) throws Exception {
		return userExerciseLogRepository.findByUserIdAndExerciseDateBetween(userId, startDate, endDate);
	}

	@Override
	public List<UserExerciseLog> queryUserExerciseByUserCourseId(String userCourseId) throws Exception {
		return userExerciseLogRepository.findByUserCourseIdAndState(userCourseId, State.valid);
	}

	@Override
	public void updateUserExercise(List<UserExerciseLog> userExerciseLogs) throws Exception {
		userExerciseLogRepository.save(userExerciseLogs);
	}
}
