package com.chauyiu1994.onlineBidUsersService.models.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddFriendRequest {

    @NonNull
    private String friendId;
}
