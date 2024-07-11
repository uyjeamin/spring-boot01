package com.example.springboot01.domain.auth.service;


import com.example.springboot01.domain.auth.domain.RefreshToken;
import com.example.springboot01.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.springboot01.domain.exception.InvalidRefreshTokenException;
import com.example.springboot01.domain.exception.RefreshTokenNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReissueService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperty jwtProperty;

    @Transactional
    public TokenResponse reissue(String refreshToken, Role role) {
        String parseToken = jwtTokenProvider.parseToken(refreshToken);
        if(parseToken == null) {
            throw InvalidRefreshTokenException.EXCEPTION;
        }

        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(jwtTokenProvider.parseToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(redisRefreshToken.getAccountId(), role);
        redisRefreshToken.updateToken(newRefreshToken, jwtProperty.getRefreshExp());

        String newAccessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getAccountId(), role);

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

}
}
