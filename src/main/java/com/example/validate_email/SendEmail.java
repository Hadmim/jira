package com.example.validate_email;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Profiles;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import  com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.core.env.Environment;
import  com.sendgrid.helpers.mail.objects.Attachments;
import  org.apache.commons.codec.binary.Base64;

import javax.annotation.PostConstruct;

@Service
public class SendEmail {


    private static final Logger logger = LoggerFactory.getLogger(SendEmail.class);

    @PostConstruct
    public Boolean sendEmail(Mail mail) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification

      /* Email from = new Email(EmailSers);
        Email to = new Email(EmailDes);
        Content cnt;
        if (!content.equals(Jsoup.parse(content).text())) {
            // There was HTML in the text.
           cnt = new Content("text/html", content);
        }
        else{
           cnt = new Content("text/plain", content);
        }

        Mail mail = new Mail(from, subject, to, cnt);
        for (Attachments atch:atchs){
            mail.addAttachments(atch);
        }*/
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
