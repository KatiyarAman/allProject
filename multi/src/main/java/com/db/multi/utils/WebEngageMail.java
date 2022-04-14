package com.db.multi.utils;

import com.db.multi.config.ApplicationConfig;
import com.db.multi.model.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthStatus;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;


@Component
public class WebEngageMail {

    @Autowired
    ApplicationConfig config;

    @Autowired
    ObjectMapper objectMapper;
    private static  final Logger logger= LoggerFactory.getLogger(WebEngageMail.class);
    private static final String BEARER="Bearer ";

    public String  sentMail( EmailRequest emailRequest) throws JsonProcessingException {
        JSONObject data= new JSONObject();
        data.put("ttl",emailRequest.getTtl());
        data.put("userId",emailRequest.getUserId());


        JSONObject overrideData= new JSONObject();
        overrideData.put("email",emailRequest.getUserEmail());

        JSONObject context= new JSONObject();
        context.put("token",emailRequest.getToken());

        overrideData.put("context",context);
        data.put("overrideData",overrideData);

        String json=objectMapper.writeValueAsString(data);

        System.out.println("*****"  +json);

        String response= sendPost(config.getWeb_engage_url(),json,config.getWeb_engage_auth());
        return response;

    }


    public String sendPost(String url, String json, String auth) {
        int httpResponseCode = 500;
        String response = null;
        try {
            URI uri = new URI(url);
            logger.debug(url);
            URL objUrl = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            if(auth != null) {
                logger.info("web engage api Authoristion token with bearer "+BEARER+auth);
                con.setRequestProperty("Authorization", BEARER+auth);
            }
            con.connect();

            logger.debug("Calling with request body " + json);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(json);
            wr.flush();
            wr.close();

            httpResponseCode= con.getResponseCode();
            logger.debug("Https response code " + httpResponseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }
            in.close();

            response = content.toString();
            if( StringUtils.isNotEmpty(response) ){
                logger.debug("Response from API url " + url +" "+ response);
            }else{
                logger.debug("Response from API url " + url +" is null" );
                return response;
            }

            if(!(httpResponseCode == 200) ){
                response = null;

            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception occured from API url " + url +" is "+ e.getMessage() );
            return null;
        }
        return response;
    }
}
