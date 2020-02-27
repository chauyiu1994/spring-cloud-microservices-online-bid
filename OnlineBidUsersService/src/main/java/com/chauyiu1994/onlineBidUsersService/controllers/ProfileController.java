package com.chauyiu1994.onlineBidUsersService.controllers;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import com.chauyiu1994.onlineBidUsersService.mappers.ProfileMapper;
import com.chauyiu1994.onlineBidUsersService.models.profile.AddFriendRequest;
import com.chauyiu1994.onlineBidUsersService.models.profile.GeneralProfileRequest;
import com.chauyiu1994.onlineBidUsersService.models.profile.GeneralProfileResponse;
import com.chauyiu1994.onlineBidUsersService.security.JWTUtil;
import com.chauyiu1994.onlineBidUsersService.services.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ProfileController {

    private ProfileService profileService;
    private ProfileMapper profileMapper;
    private JWTUtil jwtUtil;

    @DeleteMapping("/users/{userId}/profile/{profileId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String userId, @PathVariable String profileId) {

        return profileService.deleteProfile(profileId)
                .map(result -> ResponseEntity.status(HttpStatus.OK).build())
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/user/{userId}/profile/{profileId}")
    public Mono<ResponseEntity<GeneralProfileResponse>> findById(@PathVariable String userId, @PathVariable String profileId) {

        return profileService.findById(profileId)
                .map(result -> ResponseEntity.ok(profileMapper.profileToGeneralProfileResponse(result)))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("users/{userId}/profiles")
    public Mono<ResponseEntity<GeneralProfileResponse>> create(@PathVariable String userId, @RequestBody GeneralProfileRequest request) {

        Profile profile = profileMapper.generalProfileRequestToProfile(request);
        profile.setUserId(userId);
        return profileService.createProfile(profile)
                .map(result -> ResponseEntity.ok(profileMapper.profileToGeneralProfileResponse(result)))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/users/{userId}/profiles/add-friend")
    public Mono<ResponseEntity<GeneralProfileResponse>> addFriend(@PathVariable String userId, @RequestBody AddFriendRequest request) {

        return ReactiveSecurityContextHolder.getContext()
                .switchIfEmpty(Mono.error(new IllegalStateException("Empty")))
                .flatMap(ctx -> {
                    String authToken = ctx.getAuthentication().getCredentials().toString();
                    String tokenUserId = jwtUtil.getAllClaimsFromToken(authToken).get("id", String.class);
                    System.out.println(tokenUserId);
                    System.out.println(userId);
                    if (!tokenUserId.equals(userId)) return Mono.empty();
                    return profileService.addFriend(userId, request.getFriendId())
                            .map(result -> ResponseEntity.ok(profileMapper.profileToGeneralProfileResponse(result)));
                })
                .onErrorReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
