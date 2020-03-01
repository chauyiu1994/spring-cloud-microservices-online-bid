package com.chauyiu1994.onlineBidUsersService.controllers;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import com.chauyiu1994.onlineBidUsersService.mappers.ProfileMapper;
import com.chauyiu1994.onlineBidUsersService.models.profile.FriendRequest;
import com.chauyiu1994.onlineBidUsersService.models.profile.GeneralProfileRequest;
import com.chauyiu1994.onlineBidUsersService.models.profile.GeneralProfileResponse;
import com.chauyiu1994.onlineBidUsersService.services.ProfileService;
import com.chauyiu1994.onlineBidUsersService.stream.addFriend.AddFriendModel;
import com.chauyiu1994.onlineBidUsersService.stream.SendMessageUtil;
import com.chauyiu1994.onlineBidUsersService.stream.unFriend.UnFriendModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ProfileController {

    private ProfileService profileService;
    private ProfileMapper profileMapper;

    private SendMessageUtil sendMessageUtil;
    private VerifyUtil verifyUtil;

    @DeleteMapping("/users/{userId}/profile/{profileId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String userId, @PathVariable String profileId) {

        return profileService.deleteProfile(profileId)
                .map(result -> ResponseEntity.status(HttpStatus.OK).build())
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/users/{userId}/profile/{profileId}")
    public Mono<ResponseEntity<GeneralProfileResponse>> findById(@PathVariable String userId, @PathVariable String profileId) {

        return profileService.findById(profileId)
                .map(result -> ResponseEntity.ok(profileMapper.profileToGeneralProfileResponse(result)))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("users/{userId}/profile")
    public Mono<ResponseEntity<GeneralProfileResponse>> create(@PathVariable String userId, @RequestBody GeneralProfileRequest request) {

        Profile profile = profileMapper.generalProfileRequestToProfile(request);
        profile.setUserId(userId);
        return profileService.createProfile(profile)
                .map(result -> ResponseEntity.ok(profileMapper.profileToGeneralProfileResponse(result)))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/users/{userId}/profile/{profileId}/add-friend")
    public Mono<ResponseEntity<GeneralProfileResponse>> addFriend(ServerWebExchange swe, @PathVariable String userId, @PathVariable String profileId, @RequestBody FriendRequest request) {

        if (!verifyUtil.verifyIdentity(swe, userId)) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return profileService.addFriend(userId, request.getFriendId())
                .map(profile -> {
                    sendMessageUtil.sendAddedFriendMessage(new AddFriendModel(request.getFriendId(), userId));
                    return profile;
                })
                .map(result -> ResponseEntity.ok(profileMapper.profileToGeneralProfileResponse(result)))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/users/{userId}/profile/{profileId}/un-friend")
    public Mono<ResponseEntity<GeneralProfileResponse>> unFriend(ServerWebExchange swe, @PathVariable String userId, @PathVariable String profileId, @RequestBody FriendRequest request) {

        if (!verifyUtil.verifyIdentity(swe, userId)) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        return profileService.removeFriend(userId, request.getFriendId())
                .map(profile -> {
                    sendMessageUtil.sendUnFriendMessage(new UnFriendModel(request.getFriendId(), userId));
                    return profile;
                })
                .map(result -> ResponseEntity.ok(profileMapper.profileToGeneralProfileResponse(result)))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
