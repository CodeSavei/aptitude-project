package com.aptitude.aptitude_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_results")
public class TestResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    private Integer score;
    @Column(name = "total_questions")
    private Integer totalQuestions;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public TestResult() {
        this.createdAt = LocalDateTime.now();
    }
    
    public TestResult(Long userId, Integer score, Integer totalQuestions) {
        this.userId = userId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.createdAt = LocalDateTime.now();
    }
    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    
    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
