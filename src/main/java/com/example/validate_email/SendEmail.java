package com.example.validate_email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import  com.sendgrid.helpers.mail.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.core.env.Environment;

@Service
public class SendEmail {


    private static final Logger logger = LoggerFactory.getLogger(SendEmail.class);

    @PostConstruct
    public Boolean sendEmail(Mail mail) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification

       if(SendService("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",mail))
           return true;
       else
           return false;


    }
    public  boolean SendService( @Value("${apikey}") String api,Mail mail) throws IOException {
        SendGrid sg = new SendGrid(api);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            Response response = sg.api(request);
            logger.info(response.getBody());
            return true;
        } catch (IOException ex) {
            throw ex;
        }
    }


}
