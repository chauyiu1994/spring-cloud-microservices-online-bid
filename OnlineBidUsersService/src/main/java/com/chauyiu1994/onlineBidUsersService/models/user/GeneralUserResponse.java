package com.chauyiu1994.onlineBidUsersService.models.user;

import com.chauyiu1994.onlineBidUsersService.shared.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralUserResponse {

    private String id;
    private String username;
    private Boolean enabled;
    private String nickName;
    private List<Role> roles;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedTime;
}
