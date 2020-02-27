package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.Product;
import com.chauyiu1994.onlineBidProductsService.domains.ProductWithLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuilder;
import com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories.ProductBaseRepository;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private DatabaseClient client;
    private ProductBaseRepository productBaseRepository;
    private SQLBuilder sqlBuilder;

    public Mono<Product> save(Product product) {

        return productBaseRepository.save(product)
                .flatMap(savedProduct -> findById(savedProduct.getId()));
    }

    public Mono<Product> findById(Long id) {

        return productBaseRepository.findById(id);
    }


    public Mono<Void> deleteById(Long id) {

        return productBaseRepository.deleteById(id);
    }

    public Flux<Product> findMany(SQLBuildEntity entity) {

        String sql = sqlBuilder.buildSelectSQL(entity);
        return client.execute(sql).as(Product.class).fetch().all();
    }

    @Override
    public Flux<ProductWithLike> findProductWithUserLikes(SQLJoinBuildEntity entity) {

        String sql = sqlBuilder.buildJoinSQL(entity);
        return client.execute(sql).as(ProductWithLike.class).fetch().all();
    }
}
