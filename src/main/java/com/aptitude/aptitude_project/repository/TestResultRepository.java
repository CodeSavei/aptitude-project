package com.aptitude.aptitude_project.repository;

import com.aptitude.aptitude_project.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findByUserIdOrderByCreatedAtDesc(Long userId);
}
