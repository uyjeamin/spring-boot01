package com.example.springboot01.domain.feed.service;


import com.example.springboot01.domain.feed.domain.repository.FeedRepository;
import com.example.springboot01.domain.user.facade.UserFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFeedService {

    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public void createFeed(Crea)
}
