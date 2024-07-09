package com.example.springboot01.global.exception;

import com.example.springboot01.global.error.CustomException;

public class InvalidJwtException extends CustomException {

    public static final CustomException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }

}
