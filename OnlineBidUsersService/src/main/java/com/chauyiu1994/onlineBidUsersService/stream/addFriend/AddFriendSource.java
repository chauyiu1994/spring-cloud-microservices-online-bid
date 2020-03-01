package com.chauyiu1994.onlineBidUsersService.stream.addFriend;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AddFriendSource {

    String ADD_FRIEND = "addFriend";

    @Output(ADD_FRIEND)
    MessageChannel output();
}
