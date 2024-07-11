package com.example.springboot01.domain.feed.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;

public class CannotDeleteFeedException extends CustomException {
    public static final CustomException EXCEPTION =
            new CannotDeleteFeedException();

    private CannotDeleteFeedException() {
        super(ErrorCode.CANNOT_DELETE_FEED);
    }



}
