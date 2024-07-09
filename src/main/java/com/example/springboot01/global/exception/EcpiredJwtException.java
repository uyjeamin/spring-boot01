package com.example.springboot01.global.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;

public class EcpiredJwtException extends CustomException {
    public static final CustomException EXCEPTION  =
            new ExpiredJwtException();
    private ExpiredJwtException() {
        super(ErrorCode.EXPIRD_JWT);
    }
}

