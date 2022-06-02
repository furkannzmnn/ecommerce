package com.base.ecommerce.service;

import com.base.ecommerce.model.user.Buyer;
import com.base.ecommerce.repository.BuyerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AfterLiveProcess {

    private boolean isSuccess = true;

    private static final Logger logger  = LogManager.getLogger(AfterLiveProcess.class);

    private final BuyerRepository buyerRepository;
    private final MailService mailService;

    public AfterLiveProcess(BuyerRepository buyerRepository, MailService mailService) {
        this.buyerRepository = buyerRepository;
        this.mailService = mailService;
    }

    public AfterLiveProcess afterLiveProcess(Integer buyerId) {

        Buyer buyer = buyerRepository.findById(buyerId).orElse(new Buyer());

        try {
            mailService.sendGenericMessage(buyer.getEmail(), "Welcome to Ecommerce", "your advertisement has been live");
        }catch (Exception e) {
            isSuccess = false;
        }
        return this;
    }

    public void failureHandler(Consumer<String> consumer) {
        if(!isSuccess) {
            consumer.accept("Error Occurred");
            logger.error("action failed");
        }
    }
}
