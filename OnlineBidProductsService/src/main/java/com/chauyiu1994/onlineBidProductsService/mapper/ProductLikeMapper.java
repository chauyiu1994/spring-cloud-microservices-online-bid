package com.chauyiu1994.onlineBidProductsService.mapper;

import com.chauyiu1994.onlineBidProductsService.domains.ProductLike;
import com.chauyiu1994.onlineBidProductsService.models.productLike.CreateProductLikeRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.productLike.ProductLikeResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductLikeMapper {

    ProductLikeMapper INSTANCE = Mappers.getMapper(ProductLikeMapper.class);

    ProductLike createProductLikeRequestModelToProductLike(CreateProductLikeRequestModel createProductLikeRequestModel);

    ProductLikeResponseModel productLikeToProductLikeResponseModel(ProductLike productLike);
}
