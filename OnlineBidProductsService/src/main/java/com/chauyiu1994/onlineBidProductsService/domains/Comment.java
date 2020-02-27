package com.chauyiu1994.onlineBidProductsService.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("COMMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @Column("ID")
    private Long id;

    @Column("PRODUCT_ID")
    private Long productId;

    @Column("FROM_USER_ID")
    private String fromUserId;

    @Column("CONTENT")
    private String content;

    @Column("NUM_OF_LIKE")
    private Integer numOfLikes;

    @Column("CREATE_TIME")
    private LocalDateTime createTime;

    @Column("LAST_MODIFIED_TIME")
    private LocalDateTime lastModifiedTime;
}
