package com.chauyiu1994.onlineBidUsersService.feign.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseModel {

    private Long id;
    private String sellerUserId;
    private String category;
    private String name;
    private Float price;
    private String description;
    private String buyerUserId;
    private String imageURL;
    private Integer numOfLikes;
    private Integer numOfBids;
    private Integer numOfComments;
    private Integer numOfBrowses;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedTime;
    private Long productLikeId;

    private String productURL;
}
