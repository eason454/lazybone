package com.ai.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, String>{
	
	UserExerciseLog findByUserCourseAndExerciseDate(UserCourse userCourse, Date exerciseDate);
	
	Page<UserExerciseLog> findByUserCourseOrderByExerciseDateDesc(UserCourse userCourse,Pageable page);

}
