package com.example.springboot01.domain.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;

public class RefreshTokenNotFoundException {
    public static final CustomException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
