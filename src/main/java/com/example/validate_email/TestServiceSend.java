package com.example.validate_email;


import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static  org.mockito.Mockito.*;
import static  org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceSend {

    @InjectMocks
    ServiceSend Sendtest=new ServiceSend();
    @Mock
    ValidateEmail valdmock;
    @Mock
    SendEmail sendmock;



    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testvalide() throws JSONException {
         JSONObject object= new JSONObject("{ 'email': 'hadjer@gmail.com','did_you_mean':'','format_valid':true, 'mx_found':true,'smtp_check':true, 'catch_all': false, 'role': true, 'disposable': false,'free': false, 'score': 0.8}"
         );

        when(valdmock.ValidService("hadjer@gmail.com","03017b4bd26b5e7b099922068d0b5934")).thenReturn(object);
        boolean isvalide= Sendtest.valid_mail("hadjer@gmail.com");
        assertEquals(true, isvalide);


    }
    @Test
    public void  testsend() throws IOException {
        List<Attachments> listatch=new ArrayList<>();

        Mail mail=new Mail( new Email("hadjer.mimoune@univ-constantine2.dz"),"suj", new Email("hadjer.mimoune@univ-constantine2.dz"),
                new Content("text/plain", "content"));
        when(sendmock.SendService("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",mail)).thenReturn(true);

        boolean issend=Sendtest.send_mail(mail);
        assertEquals(true, issend);
        verify(sendmock,times(1)).SendService("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",mail);
    }



}
