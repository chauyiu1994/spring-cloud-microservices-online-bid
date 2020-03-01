package com.chauyiu1994.onlineBidUsersService.repositories;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProfileRepository {

    Mono<Profile> addFriend(String id, String friendId);
    Mono<Profile> removeFriend(String id, String friendId);
    Mono<Profile> save(Profile profile);
    Mono<Profile> findByUserId(String userId);
    Mono<Profile> findById(String id);
    Mono<Void> deleteById(String id);
    Mono<Document> findByUserIdAndFriendNotAddedYet(String userId, String friendId);
    Mono<Document> findByUserIdAndFriendContains(String userId, String friendId);
}
