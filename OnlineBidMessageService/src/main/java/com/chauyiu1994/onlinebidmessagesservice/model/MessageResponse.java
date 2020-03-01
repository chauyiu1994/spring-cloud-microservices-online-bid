package com.chauyiu1994.onlinebidmessagesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private String fromUserId;
    private boolean isRead;
    private String messageType;
    private String url;
    private String content;
}
