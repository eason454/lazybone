package com.ai.service.impl;

import com.ai.domain.Course;
import com.ai.repository.CourseRepository;
import com.ai.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by eason on 2017/2/15.
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course queryCourceById(String uuid) {
        return courseRepository.findOne(uuid);
    }

    @Override
    public List<Course> queryAllCourses() {
        return courseRepository.findAll();
    }
}
