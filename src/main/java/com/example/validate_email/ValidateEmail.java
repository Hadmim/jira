package com.example.validate_email;


import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.mail.*;



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




}
