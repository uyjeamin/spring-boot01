package com.example.springboot01.domain.feed.domain.repository;

import com.example.springboot01.domain.feed.domain.Feed;
import org.springframework.data.repository.CrudRepository;

public interface FeedRepository extends CrudRepository<Feed, Integer>,FeedRepositoryCustom {
}
