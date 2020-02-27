package com.chauyiu1994.onlineBidUsersService.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAuthRequest {

    @NonNull
    private String username;

    @NonNull
    private String password;
}
