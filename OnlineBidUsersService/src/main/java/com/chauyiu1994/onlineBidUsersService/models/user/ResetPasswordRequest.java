package com.chauyiu1994.onlineBidUsersService.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {

    @NonNull
    private String username;

    @NonNull
    private String oldPassword;

    @NonNull
    private String newPassword;

    @NonNull
    private String confirmedNewPassword;
}
