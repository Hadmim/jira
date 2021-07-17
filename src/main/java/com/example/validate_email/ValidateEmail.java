package com.example.validate_email;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


@Service
public class ValidateEmail{


public Boolean  isvalide(String email) throws JSONException {
    String key = "03017b4bd26b5e7b099922068d0b5934";
  JSONObject myobj= this.ValidService(email, key);
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
public JSONObject ValidService(String email,String Key) {
    String targetURL = "https://apilayer.net/api/check?access_key=" + Key + "&email=" + email;
    HttpURLConnection connection = null;
    try {
        URL url = new URL(targetURL);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject myobj = new JSONObject(response.toString());
        return myobj;


    } catch (Exception e) {
        e.printStackTrace();
        return  new JSONObject();
    }

}





    public static void main(String[] args) throws IOException, JSONException, MessagingException {

      /*  List<String> files=new ArrayList<>();

        SendEmail send=new SendEmail();


        Mail mail_ache= EmailGeneric.email("hadjer.mimoune@univ-constantine2.dz","hadjer.mimoune@univ-constantine2.dz",
                EmailGeneric.subject2,EmailGeneric.AcheteurDomicile,
                "1001",files,"Batna","Batna",
                "0500","300","0673565993",
                "lanohoza@gmail.com","https://maps.app.goo.gl/61NhouoeniRm4DGG6",
                "","","","","","","");

        files.add("C:/Users/Hadjer/Desktop/Bon livraison.pdf");

        Mail mail_vend=   EmailGeneric.email("hadjer.mimoune@univ-constantine2.dz","hadjer.mimoune@univ-constantine2.dz",
                 EmailGeneric.subject1,EmailGeneric.VendeurDahaB,"1001",
                 files , "","","","",
                "", "","","","","",
                 "","","","");
        send.sendEmail(mail_ache);
        send.sendEmail(mail_vend);*/


    }


}
