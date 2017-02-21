package com.ai.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICoachService {
	
	Page<String> getMyStudents(String courseId,Pageable pageable)throws Exception;
}
