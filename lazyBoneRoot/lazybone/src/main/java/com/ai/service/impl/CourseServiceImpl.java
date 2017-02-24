package com.ai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ai.domain.Course;
import com.ai.repository.CourseRepository;
import com.ai.service.interfaces.ICourseService;

/**
 * Created by eason on 2017/2/15.
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;


    @Override
//    @CachePut(keyGenerator)
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Cacheable(value="query:cource:id",keyGenerator="simpleKey")
    public Course queryCourceById(String uuid) {
        return courseRepository.findOne(uuid);
    }

    @Override
    @Cacheable(value="all:course",keyGenerator="simpleKey")
    public List<Course> queryAllCourses() {
        return courseRepository.findAll();
    }
}
