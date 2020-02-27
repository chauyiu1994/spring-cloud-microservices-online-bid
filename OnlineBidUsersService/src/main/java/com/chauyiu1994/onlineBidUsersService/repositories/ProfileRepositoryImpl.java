package com.chauyiu1994.onlineBidUsersService.repositories;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;

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
                .flatMap(fdResult -> addToSet(id, friendId)
                        .flatMap(mainResult -> profileBaseRepository.findById(id))
                );
    }

    @Override
    public Mono<Profile> save(Profile profile) {

        return profileBaseRepository.save(profile);
    }

    @Override
    public Mono<Profile> findByUserId(String userId) {

        return profileBaseRepository.findByUserId(userId);
    }

    private Mono<UpdateResult> addToSet(String targetId, String friendId) {

        return Mono.from(profilesCollection.updateOne(
                eq("_id", new ObjectId(targetId)),
                Updates.addToSet("friends", friendId)
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
