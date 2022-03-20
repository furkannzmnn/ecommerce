package com.base.ecommerce.dto;

import java.util.Map;

public class BuyerCreateDto {

    private int id;
    private Map<?,?> data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }

    public static final class Builder {
        private int id;
        private Map<?,?> data;

        public Builder() {
        }

        public static Builder aBuyerCreateDto() {
            return new Builder();
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder data(Map<?, ?> data) {
            this.data = data;
            return this;
        }

        public BuyerCreateDto build() {
            BuyerCreateDto buyerCreateDto = new BuyerCreateDto();
            buyerCreateDto.data = this.data;
            buyerCreateDto.id = this.id;
            return buyerCreateDto;
        }
    }
}
