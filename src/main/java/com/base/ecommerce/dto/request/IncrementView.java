package com.base.ecommerce.dto.request;

public class IncrementView {

    private final Long userId;
    private final int productId;

    public IncrementView(Long userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public IncrementView() {
       this.userId = null;
       this.productId = 1;
    }

    public Long getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }
}
