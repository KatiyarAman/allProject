package com.db.multi.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

@Configuration
public class ApiManager {

	private static final Logger logger = LoggerFactory.getLogger(ApiManager.class);
	private final String BEARER = "Bearer ";

	public String postRequest(String apiUrl, JSONObject json) {

		logger.info("Request url for the post :{}", new Gson().toJson(apiUrl));
		logger.info("Request body :{}", json);
		String response = "";
		try {
			URI uri = new URI(apiUrl);
			URL url = uri.toURL();
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(100);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("X_AUTHORITY", "ROLE_MANAGER");
			con.setRequestProperty("X_USER_ID", "12");
			con.setRequestProperty("X_USERNAME", "aman");
			con.setRequestProperty("X_LOCATION", "Noida");
			// con.setRequestProperty("Authorization", BEARER);

			logger.info("request body");

			con.connect();

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			wr.close();

			// response from the api
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			StringBuffer in = new StringBuffer();
			String input;
			while ((input = reader.readLine()) != null) {
				in = in.append(input).append("\n");
			}

			reader.close();
			response = input.toString();
			logger.info("response " + in);
			if (StringUtils.isNotEmpty(response)) {
				logger.info("Response from API url " + url + " " + response);
			} else {
				logger.info("Response from API url " + url + " is null");
				return response;
			}
			if (!(con.getResponseCode() == 200)) {
				response = null;
			}

		} catch (Exception e) {
			logger.error("Exception occured from API url " + apiUrl + " is " + e.getMessage());
			return null;
		}
		return response;
	}

	public String getrequest(String apiUrl, JSONObject jsonObject, Map<String, String> param, Boolean isCompleteUrl) {

		logger.info("Request Url for the entity  :{}", new Gson().toJson(apiUrl));
		logger.info("Request param for the  entity  by param :{}", new Gson().toJson(param));
		String response = "";

		try {
			//is complete url 
			if (!isCompleteUrl) {
				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);

				for (Entry<String, String> entrySet : param.entrySet()) {
					builder.queryParam(entrySet.getKey(), entrySet.getValue());
				}
				apiUrl = builder.toString();
			}

			URI uri = new URI(apiUrl);
			URL url = uri.toURL();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(100);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			// lets establish the connection
			connection.connect();

			// json body
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(jsonObject.toString());
			writer.flush();
			writer.close();

			// capture the response
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuffer content = new StringBuffer();
			String input = new String();

			// capture and append the respones
			while ((input = bufferedReader.readLine()) != null) {
				content.append(input).append("\n");
			}
			bufferedReader.close();

			response = content.toString();

			if (!(200 == connection.getResponseCode())) {
				logger.info("Response code :{}", new Gson().toJson(connection.getResponseCode()));
				return null;
			}

			if (StringUtils.isNotEmpty(response))
				return response;

		} catch (Exception e) {
			logger.error("Exception occured from API url " + apiUrl + " is " + e.getMessage());
			return null;

		}
		return response;

	}
}
