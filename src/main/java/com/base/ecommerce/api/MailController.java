package com.base.ecommerce.api;

import com.base.ecommerce.core.utils.ResponseHandler;
import com.base.ecommerce.dto.request.MailRequest;
import com.base.ecommerce.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/mails")
public class MailController extends BaseRestController{

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/subscriberS")
    public ResponseEntity<Object> sendEmail(@RequestBody MailRequest mailFeedback) throws IOException {
        return ResponseHandler.jsonGenerateResponse("EMAIL SEND", HttpStatus.OK, this.mailService.sendGlobalEmail(mailFeedback));
    }
}
