package com.ai.repository;

import com.ai.domain.Course;
import com.ai.domain.UserCourse;
import com.ai.util.consts.CommonConst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by eason on 2017/2/16.
 */
public interface UserCourseRepository extends JpaRepository<UserCourse,String> {
    List<UserCourse> findByUserIdAndState(String userId,CommonConst.State state);
    UserCourse findByUserIdAndUserCourseId(String userId,Course course);
}
