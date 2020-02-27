package com.chauyiu1994.onlineBidProductsService.mapper;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import com.chauyiu1994.onlineBidProductsService.models.productDetail.CreateProductDetailRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.productDetail.ProductDetailResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.productDetail.UpdateProductDetailRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDetailMapper {

    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);

    ProductDetail createProductDetailRequestModelToProductDetail(CreateProductDetailRequestModel createProductDetailRequestModel);

    ProductDetail updateProductDetailRequestModelToProductDetail(UpdateProductDetailRequestModel updateProductDetailRequestModel);

    ProductDetailResponseModel productDetailToProductDetailResponseModel(ProductDetail productDetail);
}
