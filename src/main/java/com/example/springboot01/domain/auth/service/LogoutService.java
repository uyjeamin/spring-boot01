package com.example.springboot01.domain.auth.service;

import com.example.springboot01.domain.auth.domain.RefreshToken;
import com.example.springboot01.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.springboot01.domain.exception.RefreshTokenNotFoundException;
import com.example.springboot01.domain.user.domain.User;
import com.example.springboot01.domain.user.facade.UserFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserFacade userFacade;

    @Transactional
    public  void execute() {
        User user = userFacade.getCurrentUser();

        RefreshToken refreshToken = refreshTokenRepository.findById(user.getAccounId())
                .orElseThrow(()-> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}
