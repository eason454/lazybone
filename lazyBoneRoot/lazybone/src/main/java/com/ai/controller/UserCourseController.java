package com.ai.controller;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.service.interfaces.IExerciseService;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.CommonConst.State;
import com.ai.util.exception.ResourceExistException;
import com.ai.util.time.TimeUtils;

/**
 * Created by eason on 2017/2/16.
 */
@RestController
public class UserCourseController {
    @Autowired
    private IUserCourseService userCourseService;
//    @Autowired
//    private ICourseService service;
    
    @Autowired
    private IExerciseService exerciseService;
    
    @RequestMapping(path = "/queryAllUserCourse/{userId}")
    public List<UserCourse> queryAllUserCourse(@PathVariable("userId") String userId){
        return userCourseService.queryHistoryCourse(userId);
    }
    
    @RequestMapping(path = "/queryUserCourseValid/{userId}")
    public List<UserCourse> queryUserCourseValid(@PathVariable("userId") String userId){
        return userCourseService.queryUserCourse(userId);
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
    public void giveUpCourse(@RequestBody UserCourse userCourse) throws Exception{
        UserCourse oldUserCourse=userCourseService.findByUserIdAndUserCourse(userCourse.getUserId(),userCourse.getCourse());
        oldUserCourse.setState(State.giveup);
        oldUserCourse.setEndDate(new DateTime().toDate());
        //删除失效运动记录逻辑
        List<UserExerciseLog> userExerciseLogs= exerciseService.queryUserExerciseByUserCourseId(userCourse.getUserCourseId());
        userExerciseLogs.stream().forEach(e -> e.setState(State.giveup));
        userCourseService.save(oldUserCourse);
        exerciseService.updateUserExercise(userExerciseLogs);
    }
}
