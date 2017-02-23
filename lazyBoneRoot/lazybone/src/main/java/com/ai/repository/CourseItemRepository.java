package com.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.CourseItem;

public interface CourseItemRepository extends JpaRepository<CourseItem, String>{

}
