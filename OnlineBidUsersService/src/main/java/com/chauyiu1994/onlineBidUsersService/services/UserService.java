package com.chauyiu1994.onlineBidUsersService.services;

import com.chauyiu1994.onlineBidUsersService.shared.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> findByUsername(String username);
    Mono<UserDto> save(UserDto userDto);
    Mono<UserDto> update(UserDto userDto);
    Mono<Long> count();
}
