package com.chauyiu1994.onlineBidUsersService.models.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralProfileResponse {

    private String id;
    private String userId;
    private Boolean isPrivate;
    private Integer age;
    private String region;
    private String[] preferredTradeWay;
    private List<String> friends;
    private List<String> followingUsers;
    private List<String> followedByUsers;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedTime;
}
