package com.aptitude.aptitude_project.service;

import com.aptitude.aptitude_project.model.CourseProgress;
import com.aptitude.aptitude_project.repository.CourseProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseProgressService {
    
    @Autowired
    private CourseProgressRepository courseProgressRepository;
    
    public List<CourseProgress> getAllTopic() {
        return courseProgressRepository.findAll();
    }
    
    public List<CourseProgress> getAllByUserId(Long userId) {
        return courseProgressRepository.findByUserId(userId);
    }
    
    public Optional<CourseProgress> getTopicProgress(Long userId, String topic) {
        return courseProgressRepository.findByUserIdAndTopic(userId, topic);
    }
    
    public void saveOrUpdateProgress(CourseProgress progress) {
        Optional<CourseProgress> existing = courseProgressRepository.findByUserIdAndTopic(
            progress.getUserId(), progress.getTopic());
        
        if (existing.isPresent()) {
            CourseProgress existingProgress = existing.get();
            existingProgress.setAttempts(progress.getAttempts());
            existingProgress.setScore(progress.getScore());
            existingProgress.setCompleted(progress.isCompleted());
            courseProgressRepository.save(existingProgress);
        } else {
            courseProgressRepository.save(progress);
        }
    }
    
    // Test number methods for course completion
    public void incrementTestNumber(Long userId) {
        List<CourseProgress> progressList = courseProgressRepository.findByUserId(userId);
        
        if (progressList.isEmpty()) {
            
            CourseProgress newProgress = new CourseProgress(userId, "test", 1, 0.0, false);
            courseProgressRepository.save(newProgress);
        } else {
            
            Optional<CourseProgress> testProgress = progressList.stream()
                .filter(p -> "test".equals(p.getTopic()))
                .findFirst();
            
            if (testProgress.isPresent()) {
                CourseProgress existing = testProgress.get();
                existing.setAttempts(existing.getAttempts() + 1);
                courseProgressRepository.save(existing);
            } else {
                CourseProgress newProgress = new CourseProgress(userId, "test", 1, 0.0, false);
                courseProgressRepository.save(newProgress);
            }
        }
    }
    
    public int getCurrentTestNumber(Long userId) {
        List<CourseProgress> progressList = courseProgressRepository.findByUserId(userId);
        
        if (progressList.isEmpty()) {
            return 0;
        }
        
        return progressList.stream()
            .filter(p -> "test".equals(p.getTopic()))
            .findFirst()
            .map(CourseProgress::getAttempts)
            .orElse(0);
    }
}