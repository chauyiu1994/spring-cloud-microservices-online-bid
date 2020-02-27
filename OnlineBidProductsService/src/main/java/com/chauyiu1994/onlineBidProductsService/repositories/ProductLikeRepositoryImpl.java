package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.ProductLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuilder;
import com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories.ProductLikeBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class ProductLikeRepositoryImpl implements ProductLikeRepository {

    private DatabaseClient client;
    private ProductLikeBaseRepository productLikeBaseRepository;
    private SQLBuilder sqlBuilder;

    @Override
    public Mono<ProductLike> save(ProductLike productLike) {

        return productLikeBaseRepository.save(productLike)
                .flatMap(savedProductLike -> findById(savedProductLike.getId()));
    }

    @Override
    public Mono<ProductLike> findById(Long id) {

        return productLikeBaseRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {

        return productLikeBaseRepository.deleteById(id);
    }

    @Override
    public Flux<ProductLike> findMany(SQLBuildEntity entity) {

        String sql = sqlBuilder.buildSelectSQL(entity);
        return client.execute(sql).as(ProductLike.class).fetch().all();
    }
}
