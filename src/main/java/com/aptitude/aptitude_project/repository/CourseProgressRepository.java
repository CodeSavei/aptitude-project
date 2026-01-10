package com.aptitude.aptitude_project.repository;

import com.aptitude.aptitude_project.model.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {
    Optional<CourseProgress> findByUserId(Long userId);
}
