package com.chauyiu1994.onlineBidUsersService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("profiles")
public class Profile {

    @Id
    private String id;
    private String userId;
    private Boolean isPrivate;
    private Integer age;
    private String region;
    private String[] preferredTradeWay;
    private List<String> friends;
    private List<String> followingUsers;
    private List<String> followedByUsers;
    private LocalDateTime lastModifiedTime;
}
