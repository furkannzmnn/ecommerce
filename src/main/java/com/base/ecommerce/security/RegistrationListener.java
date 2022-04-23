package com.base.ecommerce.security;

import com.base.ecommerce.model.user.User;
import com.base.ecommerce.service.MailService;
import com.base.ecommerce.service.registiration.UserSaveService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserSaveService userSaveService;
    private final MessageSource messageSource;
    private final MailService mailService;

    public RegistrationListener(UserSaveService userSaveService, MessageSource messageSource,
                                MailService mailService) {
        this.userSaveService = userSaveService;
        this.messageSource = messageSource;
        this.mailService = mailService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void onApplicationEvent(@NotNull OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws IOException {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userSaveService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
        String message = messageSource.getMessage("message.regSucc", null, event.getLocale());
        mailService.sendGenericMessage(recipientAddress, subject, message+  " " + confirmationUrl);
    }
}
