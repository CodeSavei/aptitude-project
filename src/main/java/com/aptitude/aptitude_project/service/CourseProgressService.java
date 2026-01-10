package com.aptitude.aptitude_project.service;

import com.aptitude.aptitude_project.model.CourseProgress;
import com.aptitude.aptitude_project.repository.CourseProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseProgressService {
    
    @Autowired
    private CourseProgressRepository courseProgressRepository;
    
    public CourseProgress getOrCreateProgress(Long userId) {
        return courseProgressRepository.findByUserId(userId)
                .orElseGet(() -> {
                    CourseProgress progress = new CourseProgress(userId);
                    return courseProgressRepository.save(progress);
                });
    }
    
    public void incrementTestNumber(Long userId) {
        CourseProgress progress = getOrCreateProgress(userId);
        progress.setCurrentTestNumber(progress.getCurrentTestNumber() + 1);
        courseProgressRepository.save(progress);
    }
    
    public int getCurrentTestNumber(Long userId) {
        return getOrCreateProgress(userId).getCurrentTestNumber();
    }
}