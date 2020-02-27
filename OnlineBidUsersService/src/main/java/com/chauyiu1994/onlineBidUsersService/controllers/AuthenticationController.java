package com.chauyiu1994.onlineBidUsersService.controllers;

import com.chauyiu1994.onlineBidUsersService.mappers.UserMapper;
import com.chauyiu1994.onlineBidUsersService.models.user.*;
import com.chauyiu1994.onlineBidUsersService.services.UserService;
import com.chauyiu1994.onlineBidUsersService.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private JWTUtil jwtUtil;
    private UserService userService;
    private UserMapper userMapper;
    private VerifyUtil verifyUtil;

    @PostMapping("${login.url.path}")
    public Mono<ResponseEntity<TokenAuthResponse>> login(@RequestBody LoginAuthRequest request) {

        return verifyUtil.verifyPassword(request.getUsername(), request.getPassword())
                .map(userDetails -> ResponseEntity.ok(new TokenAuthResponse(jwtUtil.generateToken(userDetails))))
                .onErrorReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("${registration.url.path}")
    public Mono<ResponseEntity<GeneralUserResponse>> signUp(@RequestBody SignUpAuthRequest request) {

        return userService.save(userMapper.signUpAuthRequestToUserDto(request))
                .map(userDetails -> ResponseEntity.ok(userMapper.userDtoToGeneralUserResponse(userDetails)))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("${resetPassword.url.path}")
    public Mono<ResponseEntity<GeneralUserResponse>> resetPassword(@RequestBody ResetPasswordRequest request) {

        if (!request.getNewPassword().equals(request.getConfirmedNewPassword())) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        return verifyUtil.verifyPassword(request.getUsername(), request.getOldPassword())
                .flatMap(userDetails -> {
                    userDetails.setPassword(request.getNewPassword());
                    return userService.update(userDetails)
                            .map(result -> ResponseEntity.ok(userMapper.userDtoToGeneralUserResponse(result)));
                })
                .onErrorReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/count")
    public Mono<Long> count() {

        return userService.count();
    }
}
