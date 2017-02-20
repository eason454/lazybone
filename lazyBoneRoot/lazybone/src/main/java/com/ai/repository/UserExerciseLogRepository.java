package com.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.UserExerciseLog;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, String>{
	
}
