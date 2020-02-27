package com.chauyiu1994.onlineBidProductsService.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Table("PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column("ID")
    private Long id;

    @Column("SELLER_USER_ID")
    private String sellerUserId;

    @Column("CATEGORY")
    private String category;

    @Column("NAME")
    private String name;

    @Column("PRICE")
    private Float price;

    @Column("DESCRIPTION")
    private String description;

    @Column("BUYER_USER_ID")
    private String buyerUserId;

    @Column("IMAGE_URL")
    private String imageURL;

    @Column("NUM_OF_LIKE")
    private Integer numOfLikes;

    @Column("NUM_OF_BID")
    private Integer numOfBids;

    @Column("NUM_OF_COMMENT")
    private Integer numOfComments;

    @Column("NUM_OF_BROWSE")
    private Integer numOfBrowses;

    @Column("CREATE_TIME")
    private LocalDateTime createTime;

    @Column("LAST_MODIFIED_TIME")
    private LocalDateTime lastModifiedTime;
}
