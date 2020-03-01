package com.chauyiu1994.onlineBidUsersService.stream.addFriend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFriendModel implements Serializable {

    private static final long serialVersionUID = -5857383701708275796L;
    private String userId;
    private String friendId;
}
