package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.CommentLike;
import com.chauyiu1994.onlineBidProductsService.repositories.CommentLikeRepository;
import com.chauyiu1994.onlineBidProductsService.services.searchUtils.CommentLikeSearchUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private CommentLikeRepository commentLikeRepository;
    private CommentService commentService;
    private CommentLikeSearchUtil commentLikeSearchUtil;

    @Override
    public Mono<CommentLike> addCommentLike(CommentLike commentLike) {

        return commentLikeRepository.save(commentLike);
    }

    @Override
    public Mono<CommentLike> findCommentLike(Long commentLikeId) {

        return commentLikeRepository.findById(commentLikeId);
    }

    @Override
    public Flux<CommentLike> findCommentLikes(Map<String, String> paramMap) {

        return commentLikeRepository.findMany(commentLikeSearchUtil.getEntity(paramMap));
    }

    @Override
    public Mono<Void> deleteCommentLike(Long productId, Long commentId, Long commentLikeId) {

        return commentService.findComment(commentId).flatMap(foundComment -> {
            if (foundComment == null || foundComment.getProductId() != productId) return Mono.empty();
            return commentLikeRepository.deleteById(commentLikeId);
        });
    }
}
