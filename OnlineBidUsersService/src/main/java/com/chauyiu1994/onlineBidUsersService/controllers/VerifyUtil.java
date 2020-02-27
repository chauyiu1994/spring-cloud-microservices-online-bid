package com.chauyiu1994.onlineBidUsersService.controllers;

import com.chauyiu1994.onlineBidUsersService.security.PBKDF2Encoder;
import com.chauyiu1994.onlineBidUsersService.services.UserService;
import com.chauyiu1994.onlineBidUsersService.shared.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class VerifyUtil {

    private UserService userService;
    private PBKDF2Encoder encoder;

    public Mono<UserDto> verifyPassword(String username, String password) {

        return userService.findByUsername(username)
                .flatMap(userDetails -> encoder.encode(password).equals(userDetails.getPassword())
                        ? Mono.just(userDetails)
                        : Mono.empty()
                );
    }
}
