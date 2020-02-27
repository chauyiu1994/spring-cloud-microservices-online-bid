package com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories;

import com.chauyiu1994.onlineBidProductsService.domains.CommentLike;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CommentLikeBaseRepository extends ReactiveCrudRepository<CommentLike, Long> {
}
