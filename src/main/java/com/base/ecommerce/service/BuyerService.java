package com.base.ecommerce.service;

import com.base.ecommerce.dto.BuyerCreateDto;
import com.base.ecommerce.model.user.Buyer;
import com.base.ecommerce.repository.BuyerRepository;
import com.base.ecommerce.service.flows.BuyerNormalFlow;
import com.base.ecommerce.service.flows.BuyerPremiumFlow;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final BuyerAccountCalculate buyerAccountCalculate;
    private final BuyerNormalFlow buyerNormalFlow;
    private final BuyerPremiumFlow buyerPremiumFlow;


    public BuyerService(BuyerRepository buyerRepository, BuyerAccountCalculate buyerAccountCalculate, BuyerNormalFlow buyerNormalFlow, BuyerPremiumFlow buyerPremiumFlow) {
        this.buyerRepository = buyerRepository;
        this.buyerAccountCalculate = buyerAccountCalculate;
        this.buyerNormalFlow = buyerNormalFlow;
        this.buyerPremiumFlow = buyerPremiumFlow;
    }

    public void createBuyerAccount(Buyer buyer) {

        final Object accountPoint = buyerAccountCalculate.toAllocateAccountPoint(buyer.getBuyerType());

        Buyer buyerOptions = null;

        if (accountPoint instanceof BuyerPremiumFlow) {
            buyerOptions =  buyerPremiumFlow.calculatePoint();
        }

        if (accountPoint instanceof BuyerNormalFlow) {
            buyerOptions = buyerNormalFlow.calculatePoint();
        }
        
        if(buyerOptions == null ){
            throw new RuntimeException("ops");
        }

        final Buyer fromDb = new Buyer.Builder()
                .name(buyer.getName())
                .point(buyerOptions.getPoint())
                .buyerType(buyerOptions.getBuyerType())
                .lastName(buyer.getLastName())
                .phoneNumber(buyer.getPhoneNumber())
                .build();

        buyerRepository.save(fromDb);
    }

    public BuyerCreateDto listOfBuyersType(int id) {
        final Buyer fromDb = buyerRepository.findById(id).orElseThrow(
                () ->   new RuntimeException("id not found")
        );
        return new BuyerCreateDto.Builder()
                    .id(fromDb.getId())
                    .data(Map.of("type",fromDb.getBuyerType()))
                    .build();
    }

}
