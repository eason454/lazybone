package com.ai.service.interfaces;

import com.ai.domain.UserCourse;
import com.ai.util.consts.CommonConst;

import java.util.List;

/**
 * Created by eason on 2017/2/16.
 */
public interface IUserCourseService {
     List<UserCourse> queryHistoryCourse(String userId);
     UserCourse save(UserCourse userCourse);
}
