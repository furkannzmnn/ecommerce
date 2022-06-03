package com.base.ecommerce.dto.converter;

import com.base.ecommerce.dto.ProductDto;
import com.base.ecommerce.model.Product;


public final class ProductDtoConverter {

    private ProductDtoConverter() {
        throw new RuntimeException("utility");
    }
    public static ProductDto convertToProduct(Product from) {
        return new ProductDto(
                from.getId(),
                from.getProductName(),
                from.getProductTitle(),
                from.getProductDesc(),
                from.getProductPrice(),
                from.getProductStatus(),
                from.getCategory() != null ? from.getCategory().getCategoryName(): null
                );
    }

}
