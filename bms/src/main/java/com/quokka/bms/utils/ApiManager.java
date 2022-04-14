package com.quokka.bms.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class ApiManager {
    private static final Logger logger = LoggerFactory.getLogger(ApiManager.class);

    @Autowired
    RestTemplate restTemplate;

    public String getApi(String apiUrl, Map<String, String> requestParam, Boolean isCompleteUrl, Integer timeOut) {
        RestTemplate restTemplateTime = new RestTemplate(getClientHttpRequestFactory(timeOut));
        if (!isCompleteUrl) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);
            for (Map.Entry<String, String> entry : requestParam.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
            apiUrl = builder.toUriString();
        }
        try {
            logger.info("Request for Entity Url : " + apiUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<String> entity = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);
            int httpStatusCode = entity.getStatusCodeValue();
            logger.info("Status Code :{}", new Gson().toJson(httpStatusCode));
            if (httpStatusCode == (HttpStatus.OK.value()))
                return entity.getBody();
        } catch (RestClientException | IllegalStateException e) {
            logger.error("Error:", e);
        }
        return null;
    }

    public String postApi(String uri, Map<String, String> params, JSONObject body) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    builder.queryParam(param.getKey(), param.getValue());
                }
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (body == null) {
                body = new JSONObject();
            }
            HttpEntity<String> requestEntity = new HttpEntity<>(body.toString(), headers);
            logger.info("Post api: " + builder.toUriString());
            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, String.class);

            return response.getBody();
        } catch (RestClientException | IllegalStateException e) {
            logger.error("Error: ", e);

        }
        return uri;
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory(Integer timeOut) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeOut);
        return clientHttpRequestFactory;
    }

}

