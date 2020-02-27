package com.chauyiu1994.onlineBidUsersService.repositories;

import com.chauyiu1994.onlineBidUsersService.domain.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<Long> count();
    Mono<User> findByUsername(String username);
    Mono<User> save(User user);
    Mono<String> findNickNameById(String id);
}
