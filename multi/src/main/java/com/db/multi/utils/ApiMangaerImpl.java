package com.db.multi.utils;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Map;
@Service
public class ApiMangaerImpl implements ApiManagerI{
    private static final Logger logger= LogManager.getLogger(ApiMangaerImpl.class);
    @Override
    public String getApi(String apiUrl, Map<String, String> queryParam, Boolean isCompleted) {
        try{
            logger.info("Request for get Entity Urls :{}", new Gson().toJson(apiUrl));
            if(!isCompleted){
                UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(apiUrl);
                for (Map.Entry<String,String> entrySet: queryParam.entrySet()){
                    builder.queryParam(entrySet.getKey(),entrySet.getValue());
                }
                apiUrl=builder.toString();
                URI uri= new URI(apiUrl);
                URL url=uri.toURL();

                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setConnectTimeout(400);
                connection.setDefaultUseCaches(false);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("ContentType","application/json");
                connection.setRequestProperty("Accept","application/json");
                //lets establish the connection
                connection.connect();

                BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer content= new StringBuffer();
                String input= new String();
                while ((input=reader.readLine())!=null){
                    content.append(input).append("/n");
                }
                reader.close();
            }
        }catch (Exception e){}
        return null;
    }

    @Override
    public String postApi(String url, JSONObject jsonObject) {
        return null;
    }
}
