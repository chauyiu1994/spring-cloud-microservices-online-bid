package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuilder;
import com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories.BidBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class BidRepositoryImpl implements BidRepository {

    private DatabaseClient client;
    private BidBaseRepository bidBaseRepository;
    private SQLBuilder sqlBuilder;

    @Override
    public Mono<Bid> save(Bid bid) {

        return bidBaseRepository.save(bid)
                .flatMap(savedBid -> findById(savedBid.getId()));
    }

    @Override
    public Mono<Bid> findById(Long id) {

        return bidBaseRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {

        return bidBaseRepository.deleteById(id);
    }

    @Override
    public Flux<Bid> findMany(SQLBuildEntity entity) {

        String sql = sqlBuilder.buildSelectSQL(entity);
        return client.execute(sql).as(Bid.class).fetch().all();
    }
}
