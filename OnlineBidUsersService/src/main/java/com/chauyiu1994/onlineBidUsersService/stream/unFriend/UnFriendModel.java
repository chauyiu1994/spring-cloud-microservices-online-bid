package com.chauyiu1994.onlineBidUsersService.stream.unFriend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnFriendModel implements Serializable {

    private static final long serialVersionUID = -5857383701708275797L;
    private String userId;
    private String friendId;
}