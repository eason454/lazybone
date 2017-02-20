package com.ai.service.interfaces;

import java.util.List;

public interface ITraineeService {
	
	public List<String> getRank(String userId, String courseId) throws Exception;
}
