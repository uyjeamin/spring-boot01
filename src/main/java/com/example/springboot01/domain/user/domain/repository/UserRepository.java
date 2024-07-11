package com.example.springboot01.domain.user.domain.repository;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public class UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByAccountId(String accountId);

    Optional<User> findByEmail(String email);

    List<User> queryUserByFollowCountsLessThan(Integer key);

}

