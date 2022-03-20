package com.base.ecommerce.service;

import com.base.ecommerce.model.user.BuyerType;
import com.base.ecommerce.service.flows.BuyerNormalFlow;
import com.base.ecommerce.service.flows.BuyerPremiumFlow;
import org.springframework.stereotype.Service;

@Service
public class BuyerAccountCalculate implements BuyerAccountFlow {

    @Override
    public <T> T toAllocateAccountPoint(BuyerType buyerType) {
        return buyerType == BuyerType.NORMAL ? (T) new BuyerNormalFlow() : (T) new BuyerPremiumFlow();
    }
}
