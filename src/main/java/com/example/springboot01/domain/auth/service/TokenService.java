package com.example.springboot01.domain.auth.service;


import com.example.springboot01.domain.auth.presentation.dto.request.UserSignInRequest;
import com.example.springboot01.domain.exception.PasswordMisMatchException;
import com.example.springboot01.domain.user.domain.User;
import com.example.springboot01.domain.user.domain.exception.UserNotFoundException;
import com.example.springboot01.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperty jwtProperty;

    public TokenResponse signIn(UserSignInRequest request, Role role) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(request.getAccountId(), role);
        String refreshToken = jwtTokenProvider.generateRefreshToken(request.getAccountId(), role);
        LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(jwtProperty.getAccessExp());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(expiredAt)
                .refreshToken(refreshToken)
                .build();

    }
}
