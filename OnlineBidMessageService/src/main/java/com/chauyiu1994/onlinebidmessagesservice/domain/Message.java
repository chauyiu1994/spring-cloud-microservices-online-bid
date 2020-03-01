package com.chauyiu1994.onlinebidmessagesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("messages")
public class Message {

    @Id
    private String id;
    private String fromUserId;
    private String toUserId;
    private boolean isRead;
    private String messageType;
    private String url;
    private String content;
}
