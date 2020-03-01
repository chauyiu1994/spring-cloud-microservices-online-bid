package com.chauyiu1994.onlinebidmessagesservice.stream.unFriend;

import com.chauyiu1994.onlinebidmessagesservice.domain.Message;
import com.chauyiu1994.onlinebidmessagesservice.mappers.MessageMapper;
import com.chauyiu1994.onlinebidmessagesservice.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(UnFriendSink.class)
@AllArgsConstructor
public class UnFriendListener {

    private MessageRepository messageRepository;
    private MessageMapper messageMapper;

    @StreamListener(UnFriendSink.UN_FRIEND)
    public void handle(UnFriendModel message) {

        System.out.println(message);
        messageRepository.save(messageMapper.unFriendModelToMessage(message)).block();
    }
}
