package com.chauyiu1994.onlineBidUsersService.repositories;

import com.chauyiu1994.onlineBidUsersService.domain.User;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private MongoCollection<Document> usersCollection;
    private UserBaseRepository userBaseRepository;

    public UserRepositoryImpl(MongoClient client, UserBaseRepository userBaseRepository) {

        this.usersCollection = client.getDatabase("online_bid").getCollection("users");
        this.userBaseRepository = userBaseRepository;
    }

    public Mono<Long> count() {

        return Mono.from(usersCollection.countDocuments());
    }

    public Mono<String> findNickNameById(String id) {

        return userBaseRepository.findNickNameById(new ObjectId(id));
    }

    @Override
    public Mono<User> findByUsername(String username) {

        return userBaseRepository.findByUsername(username);
    }

    @Override
    public Mono<User> save(User user) {

        return userBaseRepository.save(user);
    }
}
