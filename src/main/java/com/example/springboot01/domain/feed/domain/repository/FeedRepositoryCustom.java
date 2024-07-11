package com.example.springboot01.domain.feed.domain.repository;

import com.example.springboot01.domain.feed.domain.Feed;

import java.time.LocalDate;
import java.util.List;

public interface FeedRepositoryCustom  {

    List<Feed> queryFeedNotLike();

    List<Feed> queryFeedViews(LocalDate createdAt);
}
