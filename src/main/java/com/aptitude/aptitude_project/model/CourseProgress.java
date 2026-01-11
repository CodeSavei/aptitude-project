package com.aptitude.aptitude_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_progress")
public class CourseProgress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "topic", nullable = false)
    private String topic;
    
    @Column(name = "attempts")
    private int attempts;
    
    @Column(name = "score")
    private double score;
    
    @Column(name = "completed")
    private boolean completed;
    
    public CourseProgress() {}
    
    public CourseProgress(Long userId, String topic, int attempts, double score, boolean completed) {
        this.userId = userId;
        this.topic = topic;
        this.attempts = attempts;
        this.score = score;
        this.completed = completed;
    }
    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    
    public int getAttempts() { return attempts; }
    public void setAttempts(int attempts) { this.attempts = attempts; }
    
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}