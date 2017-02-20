package com.ai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.UserCourse;
import com.ai.util.consts.CommonConst;

/**
 * Created by eason on 2017/2/16.
 */
public interface UserCourseRepository extends JpaRepository<UserCourse,String> {
    List<UserCourse> findByUserIdAndState(String userId,CommonConst.State state);
    UserCourse findByUserIdAndCourseId(String userId,String course);
    List<UserCourse>  findByCourseIdAndState(String courseId, CommonConst.State state);
}
