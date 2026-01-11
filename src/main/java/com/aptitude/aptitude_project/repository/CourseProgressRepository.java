package com.aptitude.aptitude_project.repository;

import com.aptitude.aptitude_project.model.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {
    
    Optional<CourseProgress> findByUserIdAndTopic(Long userId, String topic);
    
    List<CourseProgress> findByUserId(Long userId);
    
    List<CourseProgress> findByTopic(String topic);
}