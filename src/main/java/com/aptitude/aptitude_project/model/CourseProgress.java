package com.aptitude.aptitude_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_progress")
public class CourseProgress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "current_test_number")
    private Integer currentTestNumber = 1;
    
    // Constructors
    public CourseProgress() {}
    
    public CourseProgress(Long userId) {
        this.userId = userId;
        this.currentTestNumber = 1;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Integer getCurrentTestNumber() { return currentTestNumber; }
    public void setCurrentTestNumber(Integer currentTestNumber) { 
        this.currentTestNumber = currentTestNumber; 
    }
}