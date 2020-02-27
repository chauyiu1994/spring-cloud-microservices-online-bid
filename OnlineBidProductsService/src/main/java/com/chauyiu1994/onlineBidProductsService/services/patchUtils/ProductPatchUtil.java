package com.chauyiu1994.onlineBidProductsService.services.patchUtils;

import com.chauyiu1994.onlineBidProductsService.domains.Product;
import org.springframework.stereotype.Component;

// patch allow on name, description, price, imageURL
@Component
public class ProductPatchUtil {

    public boolean patchProduct(Product targetProduct, Product product, boolean isIncrNumOfBrowses) {

        return updateCategory(targetProduct, product)
                || updateName(targetProduct, product)
                || updateDescription(targetProduct, product)
                || updatePrice(targetProduct, product)
                // || updateBuyerUserId(targetProduct, product)
                || updateImageURL(targetProduct, product)
                || incrNumOfBrowses(targetProduct, isIncrNumOfBrowses);
    }

    private boolean updateCategory(Product targetProduct, Product product) {

        if (product.getCategory() != null && !product.getCategory().equals(targetProduct.getCategory())) {
            targetProduct.setCategory(product.getCategory());
            return true;
        }
        return false;
    }

    private boolean updateName(Product targetProduct, Product product) {

        if (product.getName() != null && !product.getName().equals(targetProduct.getName())) {
            targetProduct.setName(product.getName());
            return true;
        }
        return false;
    }

    private boolean updateDescription(Product targetProduct, Product product) {

        if (product.getDescription() != null && !product.getDescription().equals(targetProduct.getDescription())) {
            targetProduct.setDescription(product.getDescription());
            return true;
        }
        return false;
    }

    private boolean updatePrice(Product targetProduct, Product product) {

        if (product.getPrice() != null && !product.getPrice().equals(targetProduct.getPrice())) {
            targetProduct.setPrice(product.getPrice());
            return true;
        }
        return false;
    }

    private boolean updateBuyerUserId(Product targetProduct, Product product) {

        if (product.getBuyerUserId() != null && !product.getBuyerUserId().equals(targetProduct.getBuyerUserId())) {
            targetProduct.setBuyerUserId(product.getBuyerUserId());
            return true;
        }
        return false;
    }

    private boolean updateImageURL(Product targetProduct, Product product) {

        if (product.getImageURL() != null && !product.getImageURL().equals(targetProduct.getImageURL())) {
            targetProduct.setImageURL(product.getImageURL());
            return true;
        }
        return false;
    }

    private boolean incrNumOfBrowses(Product targetProduct, boolean isIncrNumOfBrowses) {

        if (isIncrNumOfBrowses) {
            targetProduct.setNumOfBrowses(targetProduct.getNumOfBrowses() + 1);
            return true;
        }
        return false;
    }
}
