package com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CommentBaseRepository extends ReactiveCrudRepository<Comment, Long> {
}
