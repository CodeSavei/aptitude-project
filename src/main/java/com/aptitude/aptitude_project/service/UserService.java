package com.aptitude.aptitude_project.service;

import com.aptitude.aptitude_project.model.User;
import com.aptitude.aptitude_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password));
    }
}
