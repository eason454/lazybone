package com.ai.service.impl;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;
import com.ai.repository.UserCourseRepository;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eason on 2017/2/16.
 */
@Service
public class UserCourseServiceImpl implements IUserCourseService {
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Override
    public List<UserCourse> queryHistoryCourse(String userId) {
        return userCourseRepository.findByUserIdAndState(userId, CommonConst.State.invalid);
    }

    @Override
    public UserCourse save(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    @Override
    public UserCourse findByUserIdAndUserCourse(String userId, Course course) {
        return userCourseRepository.findByUserIdAndUserCourseId(userId,course);
    }
}
