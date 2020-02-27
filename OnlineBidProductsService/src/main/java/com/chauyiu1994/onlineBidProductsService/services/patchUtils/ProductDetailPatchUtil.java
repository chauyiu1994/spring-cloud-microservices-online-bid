package com.chauyiu1994.onlineBidProductsService.services.patchUtils;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import org.springframework.stereotype.Component;

// patch only allow on description and imageURL
@Component
public class ProductDetailPatchUtil {

    public boolean patchProductDetail(ProductDetail targetProductDetail, ProductDetail productDetail) {

        return updateDescription(targetProductDetail, productDetail)
                || updateImageURL(targetProductDetail, productDetail);
    }

    private boolean updateDescription(ProductDetail targetProductDetail, ProductDetail productDetail) {

        if (productDetail.getDescription() != null && !productDetail.getDescription().equals(targetProductDetail.getDescription())) {
            targetProductDetail.setDescription(productDetail.getDescription());
            return true;
        }
        return false;
    }

    private boolean updateImageURL(ProductDetail targetProductDetail, ProductDetail productDetail) {

        if (productDetail.getImageURL() != null && !productDetail.getImageURL().equals(targetProductDetail.getImageURL())) {
            targetProductDetail.setImageURL(productDetail.getImageURL());
            return true;
        }
        return false;
    }
}
