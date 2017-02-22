package com.ai.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.CourseUserRank;


public interface CourseUserRankRepository extends JpaRepository<CourseUserRank, String> {
	Page<CourseUserRank> findByCourseIdOrderByRank(String courseId, Pageable pageable);

	void deleteByRankDate(Date date);
}
