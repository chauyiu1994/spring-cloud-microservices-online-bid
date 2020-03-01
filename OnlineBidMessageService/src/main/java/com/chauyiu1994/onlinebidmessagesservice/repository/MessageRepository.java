package com.chauyiu1994.onlinebidmessagesservice.repository;

import com.chauyiu1994.onlinebidmessagesservice.domain.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

}
