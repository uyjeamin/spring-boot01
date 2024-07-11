package com.example.springboot01.domain.feed.domain.repository;


import com.example.springboot01.domain.feed.domain.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class FeedRepositoryCustomlmpl implements FeedRepositoryCustom{

    private final JpaQueryMethodFactory query;

    @Override
    public List<Feed> queryFeedNotLke() {
        return query
                .selectFrom(feed)
                .leftJoin(like)
                .on(feed.id.eq(like.feed.id))
                where(like.id.eq(1))
                .fetch();
    }

    @Override
    public List<Feed> queryFeedViews(LocalDate createdAt) {
        return query
                .selectFrom(feed)
                .where(feed.createdAt.gt(createdAt))
                .fetch();
    }
}
