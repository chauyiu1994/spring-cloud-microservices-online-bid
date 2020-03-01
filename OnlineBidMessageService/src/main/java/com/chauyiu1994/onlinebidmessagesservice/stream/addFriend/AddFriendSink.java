package com.chauyiu1994.onlinebidmessagesservice.stream.addFriend;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface AddFriendSink {

    String ADD_FRIEND = "addFriend";

    @Input(ADD_FRIEND)
    SubscribableChannel input();
}
