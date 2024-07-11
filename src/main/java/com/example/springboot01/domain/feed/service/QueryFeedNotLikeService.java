package com.example.springboot01.domain.feed.service;


import com.example.springboot01.domain.feed.domain.repository.FeedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryFeedNotLikeService {

    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public FeedListResponse execute() {
        return new FeedListResponse(
                feedRepository.queryFeedNotLike()
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
