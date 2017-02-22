package com.ai.repository;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;
import com.ai.util.consts.CommonConst;
import com.ai.util.consts.CommonConst.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by eason on 2017/2/16.
 */
public interface UserCourseRepository extends JpaRepository<UserCourse,String> {
	
    List<UserCourse> findByUserIdAndState(String userId,CommonConst.State state);
    
    UserCourse findByUserIdAndCourseIdAndState(String userId, String course, CommonConst.State state);
    Page<UserCourse>  findByCourseIdAndState(String course, CommonConst.State state, Pageable pageable);

    Page<UserCourse>  findByCourseAndState(Course course, CommonConst.State state, Pageable pageable);
    
    UserCourse findByUserIdAndCourseId(String userId, String courseId);
    
    List<UserCourse>  findByState(CommonConst.State state);
    
    UserCourse findByUserIdAndCourseAndState(String userId, Course course, CommonConst.State state);
    
	List<UserCourse> findByUserId(String userId);

	List<UserCourse> findByCourseAndStateOrderByProcessDesc(Course course, State valid);
}
