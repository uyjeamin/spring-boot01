package com.example.springboot01.domain.user.service;


    import com.example.springboot01.domain.user.domain.repository.UserRepository;
    import com.example.studyspringboot.domain.user.domain.repository.UserRepository;
import com.example.studyspringboot.domain.user.presentation.dto.response.QueryUserElement;
import com.example.studyspringboot.domain.user.presentation.dto.response.QueryUserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

    @RequiredArgsConstructor
    @Service
    public class QueryUserListService {

        private final UserRepository userRepository;

        @Transactional(readOnly = true)
        public QueryUserListResponse execute(Integer key) {
            return new QueryUserListResponse(
                    userRepository.queryUserByFollowCountsLessThan(key)
                            .stream()
                            .map(QueryUserElement::of)
                            .collect(Collectors.toList())
            );

        }
    }

