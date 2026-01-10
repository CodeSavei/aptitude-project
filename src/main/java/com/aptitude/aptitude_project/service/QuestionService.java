package com.aptitude.aptitude_project.service;

import com.aptitude.aptitude_project.model.Question;
import com.aptitude.aptitude_project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    public List<Question> getRandomQuestions(int count) {
        List<Question> allQuestions = questionRepository.findAll();
        
        if (allQuestions.size() <= count) {
            return allQuestions;
        }
        
        Collections.shuffle(allQuestions, new Random());
        return allQuestions.subList(0, count);
    }
    
    public List<Question> getQuestionsByTopic(String topic) {
        return questionRepository.findByTopic(topic);
    }
    
    public List<Question> getRandomQuestionsByTopic(String topic, int count) {
        List<Question> questions = questionRepository.findByTopic(topic);
        
        if (questions.size() <= count) {
            return questions;
        }
        
        Collections.shuffle(questions, new Random());
        return questions.subList(0, count);
    }
}