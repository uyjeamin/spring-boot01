package com.example.springboot01.domain.user.facade;


import com.example.springboot01.domain.user.domain.User;
import com.example.springboot01.domain.user.domain.exception.UserNotFoundException;
import com.example.springboot01.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser(){
        String accounId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByAccountId(accounId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }
}
