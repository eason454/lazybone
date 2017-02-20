package com.ai.service.interfaces;

import java.util.List;

public interface ICoachService {
	
	List<String> getMyStudents(String courseId)throws Exception;
}
