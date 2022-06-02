package com.base.ecommerce.service.flows;

import com.base.ecommerce.model.user.Buyer;
import com.base.ecommerce.model.user.BuyerType;
import org.springframework.stereotype.Service;

@Service
public class BuyerNormalFlow implements IBuyerFlowType {

    private static final int FIRST_POINT = 500;

    @Override
    public Buyer calculatePoint() {
        return new Buyer.Builder()
                .buyerType(BuyerType.NORMAL)
                .point(FIRST_POINT)
                .build();
    }

}
