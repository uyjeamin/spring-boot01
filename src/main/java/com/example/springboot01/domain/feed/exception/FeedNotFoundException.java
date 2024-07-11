package com.example.springboot01.domain.feed.exception;

import com.example.springboot01.global.error.CustomException;
import com.example.springboot01.global.error.ErrorCode;

public class FeedNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new FeedNotFoundException();

    private FeedNotFoundException() {
        super(ErrorCode.FEED_NOT_FOUND);
    }


}
