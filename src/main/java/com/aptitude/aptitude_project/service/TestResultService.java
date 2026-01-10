package com.aptitude.aptitude_project.service;

import com.aptitude.aptitude_project.model.TestResult;
import com.aptitude.aptitude_project.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestResultService {
    
    @Autowired
    private TestResultRepository testResultRepository;
    
    public TestResult saveResult(TestResult result) {
        return testResultRepository.save(result);
    }
}