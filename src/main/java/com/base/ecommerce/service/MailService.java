package com.base.ecommerce.service;

import com.base.ecommerce.configure.EmailConfiguration;
import com.base.ecommerce.dto.request.MailRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class MailService {

    private static final Logger logger  = LogManager.getLogger(MailService.class);

    private static final String SYSTEM_MAIL_ADDRESS = "ozmenf97@gmail.com";
    private static final String SYSTEM_INFO_MAIL_ADDRESS = "furkan.ozmen@tapu.com";


    private final EmailConfiguration emailConfiguration;

    public MailService(EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

    public Object sendGlobalEmail(MailRequest mailRequest) throws IOException {

        final Mail mail = buildEmailInfo(
                mailRequest.getSubject(),
                mailRequest.getMail(),
                mailRequest.getContent()
        );

        Response response = getResponse(mail);

        logger.info(response.getBody());

        return mailRequest;
    }

    public void sendGenericMessage(String email, String subject ,  String body) throws IOException {
        final Mail mail = buildEmailInfo(subject, email, body);
        final Response response = getResponse(mail);
        logger.info(response.getBody());
        logger.info(String.valueOf(response.getStatusCode()));
    }
    public void sendGenericMessageForBulk(String subject, String body) throws IOException {
        final Mail mail = buildEmailInfo(subject, SYSTEM_INFO_MAIL_ADDRESS, body);
        final Response response = getResponse(mail);
        logger.info(response.getBody());
        logger.info(String.valueOf(response.getStatusCode()));
    }

    private Mail buildEmailInfo(String subject, String email, String content) {
        Email from = new Email(SYSTEM_MAIL_ADDRESS);
        Email to = new Email(email);
        Content contents = new Content("text/plain", content);
        return new Mail(from, subject,to,contents);
    }
    private Response getResponse(Mail mail) throws IOException {
        CompletableFuture<SendGrid> sendGrid = CompletableFuture.completedFuture(new SendGrid(this.emailConfiguration.getApikey()));
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
         sendGrid.thenAccept(sendGrid1 -> {
             try {
                 sendGrid1.api(request);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         });
        return sendGrid.join().api(request);
    }
}
