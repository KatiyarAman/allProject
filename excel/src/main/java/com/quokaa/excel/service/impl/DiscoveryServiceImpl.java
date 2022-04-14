package com.quokaa.excel.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.quokaa.excel.service.DiscoverService;

@Service
public class DiscoveryServiceImpl implements DiscoverService {

	private static final Logger logger = LogManager.getLogger(DiscoveryServiceImpl.class);

	@Override
	public String list(String apiUrl, Map<String, String> map) {

		return getApi(apiUrl, map);
	}

	private String getApi(String apiUrl, Map<String, String> map) {

		String response;
		try {
			String api=apiUrl;
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(api);
			for (Entry<String, String> entrySet : map.entrySet()) {
				builder.queryParam(entrySet.getKey(), entrySet.getValue());
			}
			apiUrl = builder.toUriString();
			       
			URI uri = new URI(apiUrl);
			URL url = uri.toURL();

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(200);
			con.setDefaultUseCaches(false);
			// con.setReadTimeout(100);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			// establish the connection
			con.connect();
			if (!(200 == con.getResponseCode())) {
				logger.info("response code "+con.getResponseCode());
				return null;
			}
			// capture the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			// lets capture respose and append
			StringBuilder content = new StringBuilder();
			String inputLine = new String();
			while ((inputLine = reader.readLine()) != null) {
				content.append(inputLine).append("\n");
			}
			response = content.toString();
			reader.close();

			if (StringUtils.isNotEmpty(response))
				return response;

		} catch (Exception e) {
			logger.error("error oiccured while fetch the service url ", apiUrl);
			return null;

		}
		return null;
	}

}
