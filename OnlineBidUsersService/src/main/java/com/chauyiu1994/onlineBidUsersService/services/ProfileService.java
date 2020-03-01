package com.chauyiu1994.onlineBidUsersService.services;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import reactor.core.publisher.Mono;

public interface ProfileService {

    Mono<Profile> findByUserId(String userId);
    Mono<Profile> findById(String id);
    Mono<Profile> addFriend(String id, String friendId);
    Mono<Profile> removeFriend(String id, String friendId);
    Mono<Profile> createProfile(Profile profile);
    Mono<Profile> updateProfile(Profile profile);
    Mono<Void> deleteProfile(String id);
}
