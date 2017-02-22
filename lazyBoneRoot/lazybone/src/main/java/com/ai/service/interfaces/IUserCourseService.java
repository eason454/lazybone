package com.ai.service.interfaces;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;

import java.util.List;

/**
 * Created by eason on 2017/2/16.
 */
public interface IUserCourseService {

	List<UserCourse> queryHistoryCourse(String userId);

	UserCourse save(UserCourse userCourse);

	UserCourse findByUserIdAndUserCourse(String userId, Course course);

	List<UserCourse> queryUserCourses(String userId);

	UserCourse queryByUserIdAndCouseId(String userId, String courseId);

	UserCourse findById(String userCourseId);

	List<UserCourse> queryUserCourse(String userId);

}
