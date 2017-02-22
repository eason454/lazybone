package com.ai.controller;

import com.ai.domain.Course;
import com.ai.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eason on 2017/2/15.
 */
@RestController
public class CourseController {
	
	@Autowired
	ICourseService courseService;

	@RequestMapping(path = "/saveCourse")
	public Course save(@RequestBody Course course) {
		return courseService.save(course);
	}

	@RequestMapping(path = "/queryCourseInfo/{course_id}")
	public Course queryCourseById(@PathVariable("course_id") String id) {
		return courseService.queryCourceById(id);
	}

	@RequestMapping(path = "/queryAllLBTCourses")
	public List<Course> queryAllCourses() {
		return courseService.queryAllCourses();
	}
}
