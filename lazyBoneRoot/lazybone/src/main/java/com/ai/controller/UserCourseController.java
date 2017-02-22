package com.ai.controller;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;
import com.ai.domain.UserExerciseLog;
import com.ai.service.interfaces.ICourseService;
import com.ai.service.interfaces.IExerciseService;
import com.ai.service.interfaces.IUserCourseService;
import com.ai.util.consts.CommonConst.State;
import com.ai.util.consts.ConstUtils;
import com.ai.util.exception.ResourceExistException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private ICourseService courseService;
    
    @Autowired
    private IExerciseService exerciseService;
    
    @RequestMapping(path = "/queryUserCourse/{userId}")
    public List<UserCourse> queryAllUserCourse(@PathVariable("userId") String userId){
        return userCourseService.queryHistoryCourse(userId);
    }
    
    @RequestMapping(path = "/queryUserCourse/{userId}/{state}")
    public List<UserCourse> queryUserCourseValid(@PathVariable("userId") String userId, @PathVariable("state") String state ){
        return userCourseService.queryUserCourse(userId, State.valueOf(state));
    }
    
    @RequestMapping(path = "/createUserCourse/{courseId}/{userId}")
    public UserCourse saveUserCourse(@PathVariable("courseId") String courseId,@PathVariable("userId") String userId ){
        //whether exists
        UserCourse oldUserCourse = userCourseService.findByUserIdAndCourseId(userId, courseId);
        if(oldUserCourse!=null){
            throw new ResourceExistException(oldUserCourse.getUserCourseId());
        }
        UserCourse userCourse = new UserCourse();
        Course course = courseService.queryCourceById(courseId);
        userCourse.setState(State.valid);
        userCourse.setCourse(course);
        userCourse.setUserId(userId);
        int days = course.getCourseDays();
        Date endDate = ConstUtils.addDay(new Date(), days);
        userCourse.setEndDate(endDate);
        UserCourse userCourseResult=userCourseService.save(userCourse);
        userCourseResult.getCourse();//load course
        return userCourseResult;
    }

    @RequestMapping(path = "/giveUpCourse/{userCourseId}")
    public void giveUpCourse(@PathVariable("userCourseId") String  userCourseId) throws Exception{
        UserCourse userCourse=userCourseService.findById(userCourseId);
        userCourse.setState(State.giveup);
        userCourse.setEndDate(new DateTime().toDate());
        //删除失效运动记录逻辑
        List<UserExerciseLog> userExerciseLogs= exerciseService.queryUserExerciseByUserCourseId(userCourse.getUserCourseId());
        userExerciseLogs.stream().forEach(e -> e.setState(State.giveup));
        userCourseService.save(userCourse);
        exerciseService.updateUserExercise(userExerciseLogs);
    }
}
