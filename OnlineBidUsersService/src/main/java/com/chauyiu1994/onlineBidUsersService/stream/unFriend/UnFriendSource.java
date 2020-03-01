package com.chauyiu1994.onlineBidUsersService.stream.unFriend;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UnFriendSource {

    String UN_FRIEND = "unFriend";

    @Output(UN_FRIEND)
    MessageChannel output();
}
