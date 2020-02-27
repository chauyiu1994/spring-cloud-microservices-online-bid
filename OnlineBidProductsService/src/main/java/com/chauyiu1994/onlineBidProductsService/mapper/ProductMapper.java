package com.chauyiu1994.onlineBidProductsService.mapper;

import com.chauyiu1994.onlineBidProductsService.domains.Product;
import com.chauyiu1994.onlineBidProductsService.domains.ProductWithLike;
import com.chauyiu1994.onlineBidProductsService.models.product.CreateProductRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.product.ProductResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.product.UpdateProductRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product createProductRequestModelToProduct(CreateProductRequestModel createProductRequestModel);

    Product updateProductRequestModelToProduct(UpdateProductRequestModel updateProductRequestModel);

    ProductResponseModel productToProductResponseModel(Product product);

    ProductResponseModel productWithLikeToProductResponseModel(ProductWithLike productWithLike);
}
