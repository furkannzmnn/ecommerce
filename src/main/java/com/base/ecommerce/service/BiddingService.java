package com.base.ecommerce.service;

import com.base.ecommerce.model.Product;
import com.base.ecommerce.service.payment.PaymentFlowProcess;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class BiddingService {

    private final ProductService productService;

    public BiddingService(ProductService productService) {
        this.productService = productService;
    }

    public BiddingService placeBid(int productId) {

        // get product
        Product product = productService.findByIdProduct(productId);

        PaymentFlowProcess process = new PaymentFlowProcess(product).startPaymentFlowProcess();

        process.getSuccessPaymentFlowProcess().forEach((e,k) ->  System.out.println(e + " " + k));


        // bir takım business logicler.....


        return this;

    }
}
