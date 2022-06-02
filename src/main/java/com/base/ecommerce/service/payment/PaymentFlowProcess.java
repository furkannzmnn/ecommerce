package com.base.ecommerce.service.payment;

import com.base.ecommerce.model.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public final class PaymentFlowProcess {

    private static final Logger LOGGER = Logger.getLogger(PaymentFlowProcess.class.getName());

    public static final String PAYMENT_FLOW_PROCESS_ID = "paymentFlowProcessId";

    private final Product product;
    private final Map<String, Object> successPaymentFlowProcess = new LinkedHashMap<>();
    private final Map<String, Object> failurePaymentFlowProcess = new LinkedHashMap<>();


    public PaymentFlowProcess startPaymentFlowProcess() {
        if (this.product != null) {
            boolean isAfter = Objects.requireNonNull(product.getProductTimeExpired()).isAfter(LocalDateTime.now());
            if (isAfter) {
                product.setProductTimeExpired(LocalDateTime.now().plusMinutes(5));
                failurePaymentFlowProcess.put("timeExpired", product.getId());
                LOGGER.log(java.util.logging.Level.INFO, "Product time expired: " + PAYMENT_FLOW_PROCESS_ID);
                return this;
            }
            successPaymentFlowProcess.put("product", product.getId());
            return this;
        }
        return null;
    }

    public PaymentFlowProcess() {
        this.product = null;
    }


    public PaymentFlowProcess(Product product) {
        this.product = product;
    }

    public Map<String, Object> getSuccessPaymentFlowProcess() {
        return successPaymentFlowProcess;
    }


    public Map<String, Object> getFailurePaymentFlowProcess() {
        return failurePaymentFlowProcess;
    }

}
