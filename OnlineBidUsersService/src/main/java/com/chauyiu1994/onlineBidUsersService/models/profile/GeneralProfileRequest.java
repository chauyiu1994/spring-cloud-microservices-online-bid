package com.chauyiu1994.onlineBidUsersService.models.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralProfileRequest {

    private Boolean isPrivate;
    private Integer age;
    private String region;
    private String[] preferredTradeWay;
}
