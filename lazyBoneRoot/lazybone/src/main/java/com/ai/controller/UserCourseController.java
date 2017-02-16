package com.ai.controller;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.service.interfaces.ICourseService;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.CommonConst.*;
import com.ai.util.time.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by eason on 2017/2/16.
 */
@RestController
public class UserCourseController {
    @Autowired
    private IUserCourseService userCourseService;
    @Autowired
    private ICourseService service;
    @PostMapping(path = "/queryMyHistoryExerciseInfo")
    public List<UserCourse> queryHistoryCourse(@RequestBody String userId){
        return userCourseService.queryHistoryCourse(userId);
    }
    @PostMapping(path = "/insertUserCourse")
    public UserCourse saveUserCourse(@RequestBody UserCourse userCourse){
        //construct UserCourse
        userCourse.setState(State.invalid);
            //caclulate endDate
              //inquiry course days
        int days=userCourse.getCourse().getCourseDays();
        Date endDate= TimeUtils.getEndDateWithDays(days);
        userCourse.setEndDate(endDate);
        //generate today exercise log
        return userCourseService.save(userCourse);
    }
}
