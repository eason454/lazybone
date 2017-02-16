package com.ai.service.interfaces;

import com.ai.domain.Course;

import java.util.List;
import java.util.UUID;

/**
 * Created by eason on 2017/2/15.
 */
public interface ICourseService {
    Course save(Course course);
    Course queryCourceById(String uuid);
    List<Course> queryAllCourses();
}
