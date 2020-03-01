package com.chauyiu1994.onlinebidmessagesservice.stream.unFriend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnFriendModel {

    private static final long serialVersionUID = -5857383701708275797L;
    private String userId;
    private String friendId;
}
