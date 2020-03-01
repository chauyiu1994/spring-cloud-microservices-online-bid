package com.chauyiu1994.onlineBidUsersService.repositories;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;

import static com.mongodb.client.model.Filters.*;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    private ProfileBaseRepository profileBaseRepository;
    private MongoCollection<Document> profilesCollection;

    public ProfileRepositoryImpl(MongoClient client, ProfileBaseRepository profileBaseRepository) {

        this.profilesCollection = client.getDatabase("online_bid").getCollection("profiles");
        this.profileBaseRepository = profileBaseRepository;
    }

    @Override
    public Mono<Profile> addFriend(String id, String friendId) {

        return addToSet(friendId, id)
                .flatMap(fdResult -> addToSet(id, friendId))
                .flatMap(mainResult -> profileBaseRepository.findByUserId(id));
    }

    @Override
    public Mono<Profile> removeFriend(String id, String friendId) {

        return removeFromSet(friendId, id)
                .flatMap(fdResult -> removeFromSet(id, friendId))
                .flatMap(mainResult -> profileBaseRepository.findByUserId(id));
    }

    @Override
    public Mono<Profile> save(Profile profile) {

        return profileBaseRepository.save(profile);
    }

    @Override
    public Mono<Profile> findByUserId(String userId) {

        return profileBaseRepository.findByUserId(userId);
    }

    @Override
    public Mono<Document> findByUserIdAndFriendNotAddedYet(String userId, String friendId) {

        return Mono.from(profilesCollection.find(
                and(
                        eq("userId", userId),
                        nin("friends", Collections.singletonList(friendId))
                )
        ));
    }

    @Override
    public Mono<Document> findByUserIdAndFriendContains(String userId, String friendId) {

        return Mono.from(profilesCollection.find(
                and(
                        eq("userId", userId),
                        in("friends", Collections.singleton(friendId))
                )
        ));
    }

    private Mono<UpdateResult> addToSet(String targetId, String friendId) {

        return Mono.from(profilesCollection.updateOne(
                eq("userId", targetId),
                Updates.addToSet("friends", friendId)
        ));
    }

    private Mono<UpdateResult> removeFromSet(String targetId, String friendId) {

        return Mono.from(profilesCollection.updateOne(
                eq("userId", targetId),
                Updates.pull("friends", friendId)
        ));
    }

    @Override
    public Mono<Profile> findById(String id) {

        return profileBaseRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(String id) {

        return profileBaseRepository.deleteById(id);
    }
}
