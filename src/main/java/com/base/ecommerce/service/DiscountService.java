package com.base.ecommerce.service;

import com.base.ecommerce.core.utils.ContextAware;
import com.base.ecommerce.dto.ErrorCode;
import com.base.ecommerce.exception.customException.DiscountedInvalidException;
import com.base.ecommerce.model.Product;
import com.base.ecommerce.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountService {

    private final ProductService productService;

    public DiscountService(ProductService productService) {
        this.productService = productService;
    }

    public void applyDiscountedForProduct(BigDecimal discountedPrice, int productId) {

        Product product = productService.findByIdProduct(productId);

        BigDecimal price = product.getProductPrice().getPrice();

        if (discountedPrice.compareTo(product.getProductPrice().getPrice()) > price.intValue()) {
            throw new DiscountedInvalidException(ErrorCode.DISCOUNTED_INVALID_VALUE.name());
        }

        product.getProductPrice().setDiscountedPrice(discountedPrice);

        ContextAware.getBean(AdvertisementRepository.class).save(product);

    }
}
