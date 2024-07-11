package com.example.springboot01.domain.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;

public class InvalidRefreshTokenException extends CustomException {
    public static final CustomException EXCEPTION =
            new InvalidRefreshTokenException();

    private InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
