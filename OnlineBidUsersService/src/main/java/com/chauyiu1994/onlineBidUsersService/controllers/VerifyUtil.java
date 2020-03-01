package com.chauyiu1994.onlineBidUsersService.controllers;

import com.chauyiu1994.onlineBidUsersService.security.JWTUtil;
import com.chauyiu1994.onlineBidUsersService.security.PBKDF2Encoder;
import com.chauyiu1994.onlineBidUsersService.security.SecurityContextRepository;
import com.chauyiu1994.onlineBidUsersService.services.UserService;
import com.chauyiu1994.onlineBidUsersService.shared.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class VerifyUtil {

    private UserService userService;
    private PBKDF2Encoder encoder;
    private JWTUtil jwtUtil;
    private SecurityContextRepository securityContextRepository;

    public Mono<UserDto> verifyPassword(String username, String password) {

        return userService.findByUsername(username)
                .flatMap(userDetails -> encoder.encode(password).equals(userDetails.getPassword())
                        ? Mono.just(userDetails)
                        : Mono.empty()
                );
    }

    public boolean verifyIdentity(ServerWebExchange swe, String userId) {

        String authToken = securityContextRepository.getToken(swe);
        String tokenUserId = jwtUtil.getAllClaimsFromToken(authToken).get("id", String.class);
        if (tokenUserId.equals(userId)) return true;
        return false;
    }
}
