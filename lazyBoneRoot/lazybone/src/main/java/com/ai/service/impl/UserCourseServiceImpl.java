package com.ai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;
import com.ai.repository.CourseRepository;
import com.ai.repository.UserCourseRepository;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.CommonConst.State;

/**
 * Created by eason on 2017/2/16.
 */
@Service
public class UserCourseServiceImpl implements IUserCourseService {
    
	@Autowired
    private UserCourseRepository userCourseRepository;
	@Autowired
    private CourseRepository courseRepository;
    
    @Override
    public List<UserCourse> queryHistoryCourse(String userId) {
        return userCourseRepository.findByUserId(userId);
    }

    @Override
    public List<UserCourse> queryUserCourse(String userId) {
        return userCourseRepository.findByUserIdAndState(userId, State.valid);
    }
    
    @Override
    public UserCourse save(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    @Override
    public UserCourse findByUserIdAndUserCourse(String userId, Course course) {
        return userCourseRepository.findByUserIdAndCourseId(userId,course.getId());
    }

	@Override
	public List<UserCourse> queryUserCourses(String userId) {
		return userCourseRepository.findByUserIdAndState(userId, State.valid);
	}

	@Override
	public UserCourse queryByUserIdAndCouseId(String userId, String courseId) {
		return userCourseRepository.findByUserIdAndCourseAndState(userId, courseRepository.findOne(courseId), State.valid);
	}

	@Override
	public UserCourse findById(String userCourseId) {
		return userCourseRepository.findOne(userCourseId);
	}

	@Override
	public UserCourse findByUserIdAndCourseId(String userId, String courseId) {
		return userCourseRepository.findByUserIdAndCourseIdAndState(userId, courseId, State.valid);
	}

}
