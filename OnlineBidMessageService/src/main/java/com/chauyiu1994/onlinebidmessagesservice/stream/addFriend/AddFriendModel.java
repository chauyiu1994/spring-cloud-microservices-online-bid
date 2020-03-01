package com.chauyiu1994.onlinebidmessagesservice.stream.addFriend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFriendModel {

    private static final long serialVersionUID = -5857383701708275796L;
    private String userId;
    private String friendId;
}
