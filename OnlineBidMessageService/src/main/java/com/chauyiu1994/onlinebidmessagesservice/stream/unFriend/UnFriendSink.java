package com.chauyiu1994.onlinebidmessagesservice.stream.unFriend;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface UnFriendSink {

    String UN_FRIEND = "unFriend";

    @Input(UN_FRIEND)
    SubscribableChannel input();
}
