package com.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.CourseUserRank;


public interface CousreUserRankRepository extends JpaRepository<CourseUserRank, String> {
	
}
