package com.base.ecommerce.service;

import com.base.ecommerce.model.user.BuyerType;

public interface BuyerAccountFlow {

    <T> T toAllocateAccountPoint(BuyerType buyerType);
}
