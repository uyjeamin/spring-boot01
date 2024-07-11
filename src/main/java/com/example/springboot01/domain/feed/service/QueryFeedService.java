package com.example.springboot01.domain.feed.service;

import com.example.springboot01.domain.feed.domain.Feed;
import com.example.springboot01.domain.feed.domain.repository.FeedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryFeedService {

    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public FeedListResponse getFeedList() {
        List<FeedResponse> feedList = feedRepository.findAllByJoinFetch()
                .stream()
                .map(this::buildFeedList)
                .collect(Collectors.toList());

        return new FeedListResponse(feedList);
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
