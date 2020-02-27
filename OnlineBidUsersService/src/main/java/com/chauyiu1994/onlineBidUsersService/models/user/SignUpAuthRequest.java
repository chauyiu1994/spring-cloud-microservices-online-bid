package com.chauyiu1994.onlineBidUsersService.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpAuthRequest {

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String nickName;

    @NonNull
    private List<String> roles;
}
