package com.chauyiu1994.onlineBidUsersService.repositories;

import com.chauyiu1994.onlineBidUsersService.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserBaseRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUsername(String username);

    @Query(value = "{'_id': ?0}", fields = "{'_id': 0, 'nickName': 1}")
    Mono<String> findNickNameById(ObjectId id);
}
