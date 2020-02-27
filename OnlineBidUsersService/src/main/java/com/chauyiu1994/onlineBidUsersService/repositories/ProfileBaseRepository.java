package com.chauyiu1994.onlineBidUsersService.repositories;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProfileBaseRepository extends ReactiveMongoRepository<Profile, String> {

    Mono<Profile> findByUserId(String userId);
}
