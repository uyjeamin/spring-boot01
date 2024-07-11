package com.example.springboot01.domain.feed.service;

import com.example.springboot01.domain.feed.domain.Feed;
import com.example.springboot01.domain.feed.domain.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryFeedTestService {

    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public FeedListResponse execute(LocalDate createdAt) {
        return new FeedListResponse(
                feedRepository.queryFeedViews(createdAt)
                        .stream()
                        .map(this::buildFeedList)
                        .collect(Collectors.toList())
        );
    }

    private FeedResponse buildFeedList(Feed feed) {
        return FeedResponse.builder()
                .feedId(feed.getId())
                .title(feed.getTitle())
                .views(feed.getViews())
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .likeCounts(feed.getLikeCounts())
                .unLikeCounts(feed.getUnLikeCounts())
                .name(feed.getUser().getName())
                .build();
    }

}
