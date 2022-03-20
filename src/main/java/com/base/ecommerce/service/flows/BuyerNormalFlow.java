package com.base.ecommerce.service.flows;

import com.base.ecommerce.model.user.Buyer;
import com.base.ecommerce.model.user.BuyerType;
import org.springframework.stereotype.Service;

@Service
public class BuyerNormalFlow implements IBuyerFlowType {

    private static final int FIRST_POINT = 500;

    @Override
    public Buyer calculatePoint() {
        int count = FIRST_POINT;

        Buyer from = new Buyer.Builder()
                .buyerType(BuyerType.NORMAL)
                .point(count)
                .build();

        return from;
    }

}
