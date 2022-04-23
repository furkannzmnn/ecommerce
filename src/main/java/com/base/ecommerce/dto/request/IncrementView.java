package com.base.ecommerce.dto.request;

public class IncrementView {

    private final ThreadLocal<Integer> increment = new ThreadLocal<>();
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

    public void increment() {
        increment.set(increment.get() + 1);
    }

    public void decrement() {
        increment.set(increment.get() - 1);
    }

    public int get() {
        return increment.get();
    }

    public Long getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }
}
