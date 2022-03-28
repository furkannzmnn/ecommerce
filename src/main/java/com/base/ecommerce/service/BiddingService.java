package com.base.ecommerce.service;

import com.base.ecommerce.model.Product;
import com.base.ecommerce.service.payment.PaymentFlowProcess;
import reactor.core.publisher.Mono;

import java.util.Map;

public class BiddingService {

    private final ProductService productService;

    public BiddingService(ProductService productService) {
        this.productService = productService;
    }

    public BiddingService placeBid(int productId) {

        // get product
        Product product = productService.findByIdProduct(productId);

        PaymentFlowProcess process = new PaymentFlowProcess(product).startPaymentFlowProcess();

        assert process != null;
        Mono<Map<String, Object>> mono = Mono.just(process.getFailurePaymentFlowProcess());

        mono.subscribe(System.out::println);

        return this;

    }
}
