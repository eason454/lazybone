package com.ai.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.UserExerciseLog;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, String>{
	
	UserExerciseLog findByUserCourseIdAndExerciseDate(String userCourseId, Date exerciseDate);
	
	Page<UserExerciseLog> findByUserCourseIdOrderByExerciseDateDesc(String userCourseId,Pageable page);

	UserExerciseLog findByCourseIdAndUserIdAndExerciseTypeAndExerciseDate(String courseId, String userId, String exerciseType ,Date data);

	List<UserExerciseLog> findByCourseItemIdAndUserCourseId(String courseItemId, String userCourseId);
}
