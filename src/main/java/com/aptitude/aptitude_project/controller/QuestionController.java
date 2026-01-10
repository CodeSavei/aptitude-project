package com.aptitude.aptitude_project.controller;

import com.aptitude.aptitude_project.model.Question;
import com.aptitude.aptitude_project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;
    
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
    
    @GetMapping("/random")
    public ResponseEntity<List<Question>> getRandomQuestions(
            @RequestParam(defaultValue = "5") int count) {
        return ResponseEntity.ok(questionService.getRandomQuestions(count));
    }
    
    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<Question>> getQuestionsByTopic(@PathVariable String topic) {
        return ResponseEntity.ok(questionService.getQuestionsByTopic(topic));
    }
    
    @GetMapping("/topic/{topic}/random")
    public ResponseEntity<List<Question>> getRandomQuestionsByTopic(
            @PathVariable String topic,
            @RequestParam(defaultValue = "5") int count) {
        return ResponseEntity.ok(questionService.getRandomQuestionsByTopic(topic, count));
    }
}