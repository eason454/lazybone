package com.ai.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ai.domain.UserExerciseLog;

public interface IExerciseService {

	public Page<UserExerciseLog> getCourseDetail(String userId, String courseId, Pageable pageable) throws Exception;
}
