package com.ai.repository;

import com.ai.domain.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eason on 2017/2/16.
 */
public interface UserCourseRepository extends JpaRepository<UserCourse,String> {
}
