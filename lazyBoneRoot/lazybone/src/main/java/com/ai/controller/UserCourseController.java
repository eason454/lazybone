package com.ai.controller;

import com.ai.domain.Course;
import com.ai.domain.CourseItem;
import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.service.interfaces.ICourseService;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.CommonConst.State;
import com.ai.util.exception.ResourceExistException;
import com.ai.util.exception.ResourceNotExistException;
import com.ai.util.time.TimeUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Course course = service.queryCourceById(userCourse.getCourse().getId());
        if(course==null) throw new ResourceNotExistException(userCourse.getCourse().getId(),Course.class.getSimpleName());
        int days=course.getCourseDays();
        Date endDate= TimeUtils.getEndDateWithDays(days);
        userCourse.setEndDate(endDate);
        //generate today exercise log
        List<UserExerciseLog> exerciseLogs = new ArrayList<>();
        List<CourseItem> list=userCourse.getCourse().getCourseItems();
        generateExerciseLog(userCourse, exerciseLogs, list);
        userCourse.setUserExerciseLogs(exerciseLogs);
        UserCourse userCourseResult=userCourseService.save(userCourse);
        userCourseResult.getCourse();//load course
        return userCourseResult;
    }

    private void generateExerciseLog(UserCourse userCourse, List<UserExerciseLog> exerciseLogs, List<CourseItem> list) {
        list.stream().forEach(e -> {UserExerciseLog exerciseLog=new UserExerciseLog();
                                    exerciseLog.setExerciseType(e.getExerciseType());
                                    exerciseLog.setState(State.valid);
                                    exerciseLog.setUserCourse(userCourse);
                                    exerciseLog.setUserId(userCourse.getUserId());
                                    exerciseLogs.add(exerciseLog);});
    }

    @PostMapping(path = "/queryMyTodayExerciseInfo")
    public List<UserExerciseLog> queryCurrentExerciseInfo(@RequestBody UserCourse userCourse){
        List<UserExerciseLog> list=userCourse.getUserExerciseLogs();
        if(list.isEmpty()){
            List<UserExerciseLog> exerciseLogs = new ArrayList<>();
            List<CourseItem> courseItems=userCourse.getCourse().getCourseItems();
            this.generateExerciseLog(userCourse,exerciseLogs,courseItems);
            userCourse.setUserExerciseLogs(exerciseLogs);
            return userCourseService.save(userCourse).getUserExerciseLogs();
        }else{
            return list;
        }
    }

    @RequestMapping(path = "/giveUpCourse",method = RequestMethod.POST)
    public void giveUpCourse(@RequestBody UserCourse userCourse){
        UserCourse oldUserCourse=userCourseService.findByUserIdAndUserCourse(userCourse.getUserId(),userCourse.getCourse());
        if (oldUserCourse==null) throw new ResourceNotExistException(userCourse.getUserId(),userCourse.getClass().getSimpleName());
        oldUserCourse.setState(State.invalid);
        oldUserCourse.setEndDate(new DateTime().toDate());
        List<UserExerciseLog> userExerciseLogs=oldUserCourse.getUserExerciseLogs();
        userExerciseLogs.stream().forEach(e -> e.setState(State.invalid));
        userCourseService.save(oldUserCourse);

    }

    @PostMapping(path = "/queryMyStudents")
    public  List<String> queryUserByCourseId(@RequestBody String courseId){
        return null;
    }
}
