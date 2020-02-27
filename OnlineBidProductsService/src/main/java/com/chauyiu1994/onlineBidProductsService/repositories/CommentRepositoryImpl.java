package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import com.chauyiu1994.onlineBidProductsService.domains.CommentWithLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuilder;
import com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories.CommentBaseRepository;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private DatabaseClient client;
    private CommentBaseRepository commentBaseRepository;
    private SQLBuilder sqlBuilder;

    @Override
    public Mono<Comment> save(Comment comment) {

        return commentBaseRepository.save(comment)
                .flatMap(savedComment -> findById(comment.getId()));
    }

    @Override
    public Mono<Comment> findById(Long id) {

        return commentBaseRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {

        return commentBaseRepository.deleteById(id);
    }

    @Override
    public Flux<CommentWithLike> findCommentWithUserLikes(SQLJoinBuildEntity entity) {

        String sql = sqlBuilder.buildJoinSQL(entity);
        return client.execute(sql).as(CommentWithLike.class).fetch().all();
    }

    @Override
    public Flux<Comment> findMany(SQLBuildEntity entity) {

        String sql = sqlBuilder.buildSelectSQL(entity);
        return client.execute(sql).as(Comment.class).fetch().all();
    }
}
