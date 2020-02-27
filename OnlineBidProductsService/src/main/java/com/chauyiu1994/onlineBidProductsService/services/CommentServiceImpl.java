package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import com.chauyiu1994.onlineBidProductsService.domains.CommentWithLike;
import com.chauyiu1994.onlineBidProductsService.repositories.CommentRepository;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import com.chauyiu1994.onlineBidProductsService.services.patchUtils.CommentPathUtil;
import com.chauyiu1994.onlineBidProductsService.services.searchUtils.CommentSearchUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CommentSearchUtil commentSearchUtil;
    private CommentPathUtil commentPathUtil;

    @Override
    public Mono<Comment> addComment(Comment comment) {

        return commentRepository.save(comment);
    }

    @Override
    public Mono<Comment> findComment(Long commentId) {

        return commentRepository.findById(commentId);
    }

    @Override
    public Flux<Comment> findComments(Map<String, String> paramMap) {

        return commentRepository.findMany(commentSearchUtil.getEntity(paramMap));
    }

    @Override
    public Flux<CommentWithLike> findCommentsWithLike(Map<String, String> paramMap) {

        return commentRepository.findCommentWithUserLikes((SQLJoinBuildEntity) commentSearchUtil.getEntity(paramMap));
    }

    @Override
    public Mono<Comment> updateComment(Long productId, Long commentId, Comment comment) {

        return findComment(commentId).flatMap(targetComment -> {
            if (targetComment == null || targetComment.getProductId() != productId) return Mono.empty();
            return commentPathUtil.patchComment(targetComment, comment)
                    ? commentRepository.save(targetComment)
                    : Mono.empty();
        });
    }

    @Override
    public Mono<Void> deleteComment(Long productId, Long commentId) {

        return findComment(commentId).flatMap(targetComment -> {
            if (targetComment == null || targetComment.getProductId() != productId) return Mono.empty();
            return commentRepository.deleteById(commentId);
        });
    }
}
