package com.base.ecommerce.service.flows;

import com.base.ecommerce.model.user.Buyer;
import com.base.ecommerce.model.user.BuyerType;
import org.springframework.stereotype.Service;

@Service
public class BuyerPremiumFlow implements IBuyerFlowType{

    private static final int FIRST_POINT = 1000;

    @Override
    public Buyer calculatePoint() {
        return new Buyer.Builder()
                    .buyerType(BuyerType.PREMIUM)
                    .point(FIRST_POINT)
                    .build();
    }
}
