package com.aptitude.aptitude_project.controller;

import com.aptitude.aptitude_project.model.Question;
import com.aptitude.aptitude_project.model.TestResult;
import com.aptitude.aptitude_project.service.CourseProgressService;
import com.aptitude.aptitude_project.service.QuestionService;
import com.aptitude.aptitude_project.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
    
    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private TestResultService testResultService;
    
    @Autowired
    private CourseProgressService courseProgressService;
    
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitTest(
            @RequestBody Map<String, Object> testData) {
        
        Long userId = Long.valueOf(testData.get("userId").toString());
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> answers = (List<Map<String, Object>>) testData.get("answers");
        int totalQuestions = answers.size();
        int score = 0;
        
        // Calculate score
        for (Map<String, Object> answer : answers) {
            Long questionId = Long.valueOf(answer.get("questionId").toString());
            String selectedOption = (String) answer.get("selectedOption");
            
            List<Question> allQuestions = questionService.getAllQuestions();
            for (Question q : allQuestions) {
                if (q.getId().equals(questionId)) {
                    if (q.getCorrectOption().equalsIgnoreCase(selectedOption)) {
                        score++;
                    }
                    break;
                }
            }
        }
        
        // Save result
        TestResult result = new TestResult(userId, score, totalQuestions);
        testResultService.saveResult(result);
        
        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("total", totalQuestions);
        response.put("percentage", (score * 100.0) / totalQuestions);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/course/complete")
    public ResponseEntity<Map<String, Object>> completeCourseTest(
            @RequestBody Map<String, Object> testData) {
        
        Long userId = Long.valueOf(testData.get("userId").toString());
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> answers = (List<Map<String, Object>>) testData.get("answers");
        int totalQuestions = answers.size();
        int score = 0;
        
        // Calculate score
        for (Map<String, Object> answer : answers) {
            Long questionId = Long.valueOf(answer.get("questionId").toString());
            String selectedOption = (String) answer.get("selectedOption");
            
            List<Question> allQuestions = questionService.getAllQuestions();
            for (Question q : allQuestions) {
                if (q.getId().equals(questionId)) {
                    if (q.getCorrectOption().equalsIgnoreCase(selectedOption)) {
                        score++;
                    }
                    break;
                }
            }
        }
        
        // Save result
        TestResult result = new TestResult(userId, score, totalQuestions);
        testResultService.saveResult(result);
        
        // Increment course progress
        courseProgressService.incrementTestNumber(userId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("total", totalQuestions);
        response.put("nextTestNumber", courseProgressService.getCurrentTestNumber(userId));
        
        return ResponseEntity.ok(response);
    }
}