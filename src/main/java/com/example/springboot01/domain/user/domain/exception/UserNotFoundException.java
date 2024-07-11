package com.example.springboot01.domain.user.domain.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;

public class UserNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode .USER_NOT_FOUND);
    }

}
