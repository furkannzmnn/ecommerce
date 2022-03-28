package com.base.ecommerce.service.payment;

import com.base.ecommerce.model.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

public final class PaymentFlowProcess {

    private static final Logger LOGGER = Logger.getLogger(PaymentFlowProcess.class.getName());

    public static final String PAYMENT_FLOW_PROCESS_ID = "paymentFlowProcessId";

    private final Product product;
    private final Map<String, Object> successPaymentFlowProcess;
    private final Map<String, Object> failurePaymentFlowProcess;


    public PaymentFlowProcess startPaymentFlowProcess() {
        if (this.product != null) {
            boolean isAfter = product.getProductTimeExpired().isAfter(LocalDateTime.now());
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
        this.successPaymentFlowProcess = null;
        this.failurePaymentFlowProcess = null;
    }


    public PaymentFlowProcess(Product product) {
        this.product = product;
        this.successPaymentFlowProcess = null;
        this.failurePaymentFlowProcess = null;
    }

    public Map<String, Object> getSuccessPaymentFlowProcess() {
        return successPaymentFlowProcess;
    }


    public Map<String, Object> getFailurePaymentFlowProcess() {
        return failurePaymentFlowProcess;
    }

}
