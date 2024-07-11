package com.example.springboot01.domain.feed.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;

public class CannotModifyFeedException extends CustomException {

    public static final CustomException EXCEPTION = new CannotModifyFeedException();

        private CannotModifyFeedException() {
            super(ErrorCode.CANNOT_MODIFY_FEED);
        }

}
