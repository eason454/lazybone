package com.ai.repository;

import com.ai.domain.Course;
import com.ai.util.consts.CommonConst.State;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by eason on 2017/2/15.
 */
public interface CourseRepository extends JpaRepository<Course,String> {

	List<Course> findByState(State valid);
}
