package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.CommentLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuilder;
import com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories.CommentLikeBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepository {

    private DatabaseClient client;
    private CommentLikeBaseRepository commentLikeBaseRepository;
    private SQLBuilder sqlBuilder;

    @Override
    public Mono<CommentLike> save(CommentLike commentLike) {

        return commentLikeBaseRepository.save(commentLike)
                .flatMap(savedBid -> findById(savedBid.getId()));
    }

    @Override
    public Mono<CommentLike> findById(Long id) {

        return commentLikeBaseRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {

        return commentLikeBaseRepository.deleteById(id);
    }

    @Override
    public Flux<CommentLike> findMany(SQLBuildEntity entity) {

        String sql = sqlBuilder.buildSelectSQL(entity);
        return client.execute(sql).as(CommentLike.class).fetch().all();
    }
}