package com.ai.controller;

import com.ai.domain.Course;
import com.ai.domain.CourseItem;
import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.service.interfaces.ICourseService;
import com.ai.service.interfaces.IExerciseService;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.CommonConst;
import com.ai.util.consts.CommonConst.*;
import com.ai.util.exception.ResourceExistException;
import com.ai.util.time.TimeUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    @RequestMapping(path = "/insertUserCourse",method = RequestMethod.POST)
    public UserCourse saveUserCourse(@RequestBody UserCourse userCourse){
        //whether exists
        UserCourse oldUserCourse = userCourseService.findByUserIdAndUserCourse(userCourse.getUserId(), userCourse.getCourse());
        if(oldUserCourse!=null){
            throw new ResourceExistException(oldUserCourse.getUserCourseId());
        }
        //construct UserCourse
        userCourse.setState(State.valid);
            //caclulate endDate
              //inquiry course days
        int days=userCourse.getCourse().getCourseDays();
        Date endDate= TimeUtils.getEndDateWithDays(days);
        userCourse.setEndDate(endDate);
        //generate today exercise log
//        List<UserExerciseLog> exerciseLogs = new ArrayList<>();
//        List<CourseItem> list=userCourse.getCourse().getCourseItems();
//        generateExerciseLog(userCourse, exerciseLogs, list);
//        userCourse.setUserExerciseLogs(exerciseLogs);
        UserCourse userCourseResult=userCourseService.save(userCourse);
        userCourseResult.getCourse();//load course
        return userCourseResult;
    }

//    private void generateExerciseLog(UserCourse userCourse, List<UserExerciseLog> exerciseLogs, List<CourseItem> list) {
//        list.stream().forEach(e -> {UserExerciseLog exerciseLog=new UserExerciseLog();
//                                    exerciseLog.setExerciseType(e.getExerciseType());
//                                    exerciseLog.setState(State.valid);
//                                    exerciseLog.setUserCourse(userCourse);
//                                    exerciseLog.setUserId(userCourse.getUserId());
//                                    exerciseLog.setRequireTimes(e.getRequireTimes());
//                                    exerciseLogs.add(exerciseLog);});
//    }

      //迁移到运动记录controller
//    @PostMapping(path = "/queryMyTodayExerciseInfo")
//    public List<UserExerciseLog> queryCurrentExerciseInfo(@RequestBody UserCourse userCourse){
//        List<UserExerciseLog> list=userCourse.getUserExerciseLogs();
//        if(list.isEmpty()){
//            List<UserExerciseLog> exerciseLogs = new ArrayList<>();
//            List<CourseItem> courseItems=userCourse.getCourse().getCourseItems();
//            this.generateExerciseLog(userCourse,exerciseLogs,courseItems);
//            userCourse.setUserExerciseLogs(exerciseLogs);
//            return userCourseService.save(userCourse).getUserExerciseLogs();
//        }else{
//            return list;
//        }
//    	return null;
//    }

    @PostMapping(path = "/giveUpCourse")
    public void giveUpCourse(@RequestBody UserCourse userCourse){
        UserCourse oldUserCourse=userCourseService.findByUserIdAndUserCourse(userCourse.getUserId(),userCourse.getCourse());
        oldUserCourse.setState(State.invalid);
        oldUserCourse.setEndDate(new DateTime().toDate());
        //删除失效运动记录逻辑
        //课程失效不代表运动记录失效,而且这个批量效率太低不适合在一个请求完成
//        List<UserExerciseLog> userExerciseLogs=oldUserCourse.getUserExerciseLogs();
//        userExerciseLogs.stream().forEach(e -> e.setState(State.invalid));
//        userCourseService.save(oldUserCourse);

    }
}
