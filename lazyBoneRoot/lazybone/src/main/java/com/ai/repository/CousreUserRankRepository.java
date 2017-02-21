package com.ai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.CourseUserRank;


public interface CousreUserRankRepository extends JpaRepository<CourseUserRank, String> {
	Page<CourseUserRank> findByCourseIdOrderByRank(String courseId, Pageable pageable);
}
