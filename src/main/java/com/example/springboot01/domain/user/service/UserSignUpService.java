package com.example.springboot01.domain.user.service;

import com.example.springboot01.domain.user.domain.User;
import com.example.springboot01.domain.user.domain.repository.UserRepository;
import com.example.springboot01.domain.user.exception.UserExistException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

public class UserSignUpService {
    import com.example.studyspringboot.domain.user.domain.User;
import com.example.studyspringboot.domain.user.domain.repository.UserRepository;
import com.example.studyspringboot.domain.user.exception.UserExistException;
import com.example.studyspringboot.domain.user.presentation.dto.request.UserSignUpRequest;
import com.example.studyspringboot.global.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

    @RequiredArgsConstructor
    @Service
    public class UserSignUpService {

        private final PasswordEncoder passwordEncoder;
        private final UserRepository userRepository;

        @Transactional
        public void signUp(UserSignUpRequest request) {

            if (userRepository.findByAccountId(request.getAccountId()).isPresent() ||
                    userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw UserExistException.EXCEPTION;
            }

            userRepository.save(User.builder()
                    .accountId(request.getAccountId())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .name(request.getName())
                    .sex(request.getSex())
                    .followCounts(0)
                    .role(Role.USER)
                    .build());
        }

    }
}
