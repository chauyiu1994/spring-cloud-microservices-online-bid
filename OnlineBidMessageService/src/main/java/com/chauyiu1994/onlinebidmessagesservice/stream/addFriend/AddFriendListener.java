package com.chauyiu1994.onlinebidmessagesservice.stream.addFriend;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(AddFriendSink.class)
public class AddFriendListener {

    @StreamListener(AddFriendSink.ADD_FRIEND)
    public void handle(AddFriendModel message) {

        System.out.println(message);
    }
}
