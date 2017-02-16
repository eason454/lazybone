package com.ai.controller;

import com.ai.domain.Course;
import com.ai.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by eason on 2017/2/15.
 */
@RestController
public class CourseController {
    @Autowired
    ICourseService courseService;
    @PostMapping(path = "/saveCourse")
    public Course save(@RequestBody Course course){
        return courseService.save(course);
    }
    @GetMapping(path = "/queryCourse/{course_id}")
    public Course queryCourseById(@PathVariable("course_id") String id){
        return courseService.queryCourceById(id);
    }
}
