package com.aptitude.aptitude_project.controller;

import com.aptitude.aptitude_project.model.CourseProgress;
import com.aptitude.aptitude_project.service.CourseProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "*")
public class CourseProgressController {
    
    @Autowired
    private CourseProgressService courseProgressService;
    
    @GetMapping("/all")
    public ResponseEntity<List<CourseProgress>> getAllTopic() {
        List<CourseProgress> progressList = courseProgressService.getAllTopic();
        return ResponseEntity.ok(progressList);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CourseProgress>> getUserProgress(@PathVariable Long userId) {
        List<CourseProgress> progressList = courseProgressService.getAllByUserId(userId);
        return ResponseEntity.ok(progressList);
    }
    
    @GetMapping("/user/{userId}/topic/{topic}")
    public ResponseEntity<CourseProgress> getTopicProgress(@PathVariable Long userId, @PathVariable String topic) {
        return courseProgressService.getTopicProgress(userId, topic)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/save")
    public ResponseEntity<String> saveProgress(@RequestBody CourseProgress progress) {
        try {
            courseProgressService.saveOrUpdateProgress(progress);
            return ResponseEntity.ok("Progress saved successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving progress: " + e.getMessage());
        }
    }
}