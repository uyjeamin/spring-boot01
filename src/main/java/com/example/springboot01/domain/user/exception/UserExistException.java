package com.example.springboot01.domain.user.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;

public class UserExistException extends CustomException {

    public static final CustomException EXCEPTION = new UserExistException();

    private UserExistException();

    private UserExistException(){
        super(ErrorCode.USER_EXIST);
    }
}
