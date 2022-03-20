package com.base.ecommerce.api;

import com.base.ecommerce.service.DiscountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/discount")
public class DiscountedController extends BaseRestController{

    private final DiscountService discountService;

    public DiscountedController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/new-price")
    public void discountedPrice(BigDecimal discountedPrice, int productId) {
         discountService.applyDiscountedForProduct(discountedPrice,productId);
    }
}
