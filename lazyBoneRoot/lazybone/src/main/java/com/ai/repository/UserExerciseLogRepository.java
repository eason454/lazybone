package com.ai.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.UserExerciseLog;

import com.ai.util.consts.CommonConst.State;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, String>{
	
//	UserExerciseLog findByUserCourseIdAndExerciseDate(String userCourseId, Date exerciseDate);
	
	Page<UserExerciseLog> findByUserCourseIdOrderByExerciseDateDesc(String userCourseId,Pageable page);

	UserExerciseLog findByCourseIdAndUserIdAndExerciseTypeAndExerciseDate(String courseId, String userId, String exerciseType ,Date data);

	List<UserExerciseLog> findByCourseItemIdAndUserCourseId(String courseItemId, String userCourseId);

	List<UserExerciseLog> findByUserIdAndExerciseDateBetween(String userId, Date startDate, Date endDate);
	
	List<UserExerciseLog> findByUserCourseIdAndState(String userCourseId , State state);
	
}
