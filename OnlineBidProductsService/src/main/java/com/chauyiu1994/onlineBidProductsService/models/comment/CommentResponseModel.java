package com.chauyiu1994.onlineBidProductsService.models.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseModel {

    private String id;
    private String fromUserId;
    private Long productId;
    private String content;
    private Integer numOfLikes;
    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;
    private Long commentLikeId;

    private String commentURL;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getLastModifiedTime() {
        return this.lastModifiedTime;
    }
}
