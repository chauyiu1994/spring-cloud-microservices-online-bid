package com.chauyiu1994.onlineBidProductsService.models.commentLike;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeResponseModel {

    private String id;
    private String fromUserId;
    private Long commentId;
    private LocalDateTime createTime;

    private String likeURL;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }
}
