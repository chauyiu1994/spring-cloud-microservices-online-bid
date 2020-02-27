package com.chauyiu1994.onlineBidUsersService.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String username;
    private String password;
    private Boolean enabled;
    private String nickName;
    private List<Role> roles;
    private List<String> friends;
    private List<String> followingUsers;
    private List<String> followedByUsers;
    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;
}
