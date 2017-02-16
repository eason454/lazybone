package com.ai.controller;

import com.ai.domain.Course;
import com.ai.domain.CourseItem;
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

import java.util.ArrayList;
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
        List<UserExerciseLog> exerciseLogs = new ArrayList<>();
        List<CourseItem> list=userCourse.getCourse().getCourseItems();
        list.stream().forEach(e -> {UserExerciseLog exerciseLog=new UserExerciseLog();
                                    exerciseLog.setExerciseType(e.getExerciseType());
                                    exerciseLog.setState(State.valid);
                                    exerciseLog.setUserCourse(userCourse);
                                    exerciseLog.setUserId(userCourse.getUserId());
                                    exerciseLogs.add(exerciseLog);});
        userCourse.setUserExerciseLogs(exerciseLogs);
        return userCourseService.save(userCourse);
    }
}
