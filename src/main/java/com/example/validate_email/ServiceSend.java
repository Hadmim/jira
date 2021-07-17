package com.example.validate_email;

import com.sendgrid.helpers.mail.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ServiceSend {
    @Autowired
    ValidateEmail validate_obj;
    @Autowired
    SendEmail send_obj;

    public boolean valid_mail(String mail) throws JSONException {

        JSONObject myobj= validate_obj.ValidService(mail,"03017b4bd26b5e7b099922068d0b5934");
        if(myobj.has("error"))
            return false;
        try {
            if((myobj.getString("mx_found")=="true") && (myobj.getString("smtp_check")=="true") ){

                return  true;
            }
            else {
                return  false;
            }


        } catch (Exception ex){
            return  false;
        }
    }
   public boolean send_mail(Mail email) throws IOException {

       if(send_obj.SendService("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",email))
           return true;
       else
           return false;

   }


}
