package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuilder;
import com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories.ProductDetailBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class ProductDetailRepositoryImpl implements ProductDetailRepository {

    private DatabaseClient client;
    private ProductDetailBaseRepository productDetailBaseRepository;
    private SQLBuilder sqlBuilder;

    @Override
    public Mono<ProductDetail> save(ProductDetail productDetail) {

        return productDetailBaseRepository.save(productDetail)
                .flatMap(savedProductDetail -> findById(savedProductDetail.getId()));
    }

    @Override
    public Mono<ProductDetail> findById(Long id) {

        return productDetailBaseRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {

        String sql = sqlBuilder.buildStoredProcSQL("DELETE_PRODUCT_DETAIL", id);
        return client.execute(sql).as(Void.class).fetch().one();
    }

    @Override
    public Flux<ProductDetail> findMany(SQLBuildEntity entity) {

        String sql = sqlBuilder.buildSelectSQL(entity);
        return client.execute(sql).as(ProductDetail.class).fetch().all();
    }
}