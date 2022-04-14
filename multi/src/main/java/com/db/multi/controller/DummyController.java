package com.db.multi.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akamai.edgegrid.signer.ClientCredential;
import com.akamai.edgegrid.signer.googlehttpclient.GoogleHttpClientEdgeGridRequestSigner;
import com.db.multi.config.Cache;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/dummy/*")
public class DummyController {
	private static final Logger logger=LogManager.getLogger(DummyController.class);
	
	@Value("${site.url}")
	private String SITE_URL;
	@Value("${evict.cache.varnish.desktop.site.url}")
	private String EVICT_VARNISH_DESKTOP_SITE_URL;

	@Value("${evict.cache.varnish.mobile.site.url}")
	private String EVICT_VARNISH_MOBILE_SITE_URL;
	
	@Value("${fastly.active.status}")
	private String fastlyActive;
	
	private ClientCredential credential;
	
	@Value("${access.token}")
	private String ACCESS_TOKEN;
	;
	@Value("${client.token}")
	private String CLIENT_TOKEN;
	
	@Value("${client.secret}")
	private String CLIENT_SECRET;
	@Value("${host}")
	private String HOST;
	@PostConstruct 
	public void init() {
		credential = ClientCredential.builder()
	            .accessToken(ACCESS_TOKEN)
	            .clientToken(CLIENT_TOKEN)
	            .clientSecret(CLIENT_SECRET)
	            .host(HOST)
	            .build();
	}
	
	@GetMapping(value = "/url")
	public ResponseEntity<String> evictCacheByUrl(@RequestParam(name = "url") String url,
			@RequestParam(required = false, defaultValue = "false") Boolean evictVarnish,
			@RequestParam(required = false, defaultValue = "true") Boolean evictAkamai){
		Boolean status = evictCacheByUrls(url, evictVarnish, evictAkamai);
		String responseMsg = status ? "Cache evicted successfully." : "Cache eviction failed.";
		return ResponseEntity.ok(responseMsg);
	}
	public Boolean evictCacheByUrls(String url, Boolean evictVarnish, Boolean evictAkamai) {
		logger.info("Evict cache request for url: {} and evictVarnish: {}, evictAkamai: {} ",url, evictVarnish, evictAkamai);
		evictUrlCache(url, evictVarnish, evictAkamai);
		return true;
	}

	@Async
	public void evictUrlCache(String url, Boolean evictVarnish, Boolean evictAkamai) {
		logger.info("Async Eviction for url: {} and evictVarnish: {}, evictAkamai: {} ",url, evictVarnish, evictAkamai);
		//check if url is not for site
		if(isSiteURL(url)) {
			logger.info("is site url :"+url);
			//don't evict cache
			return;
		}
		//evict varnish cache if flag is true
		List<String> urlList = Arrays.asList(url);
		
		if(Boolean.TRUE.equals(evictAkamai)) {
			if(fastlyActive.equalsIgnoreCase("on")) {
				evictFastlyCacheWithUrls(urlList, evictVarnish);
			}else {				
				evictAkamaiCache(urlList, evictVarnish);
			}
		}else if(Boolean.TRUE.equals(evictVarnish)) {
			evictVarnishCache(urlList);
		}
	}
	
	
	private void evictAkamaiCache(List<String> urlList, boolean evictVarnish) {
		//cleaning varnish cache
		if(evictVarnish) {
			evictVarnishCache(urlList);
		}
		
		HttpResponse response = null;
		try {
			HttpTransport httpTransport = new ApacheHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			String akamailUri = "https://"+"url/production";
			URI uri = URI.create(akamailUri);
			logger.info("uri :"+uri);
			Map<String, List<String>> map = new TreeMap<>();
			map.put("objects", urlList);
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			String requestBody = gson.toJson(map);
			logger.info("akamai url "+akamailUri+ " the request body" +requestBody);
//			HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(uri), ByteArrayContent.fromString("application/json", requestBody));
//			GoogleHttpClientEdgeGridRequestSigner requestSigner = new GoogleHttpClientEdgeGridRequestSigner(credential);
//			requestSigner.sign(request);
//			response = request.execute();
			// response status code
			logger.info("akamai response code "+response.getStatusCode()); 
			String text = null;
			try (Scanner scanner = new Scanner(response.getContent(), StandardCharsets.UTF_8.name())) {
				text = scanner.useDelimiter("\\A").next();
			}
			logger.info("akamai response "+text); // response body
		}catch (Exception e) {
			logger.error("exception occured in akamai cache eviction api: "+urlList +"with response :"+ response+ " exception :",e);
			e.printStackTrace();
		}
	}
	
	
	private boolean isSiteURL(String url) {
		List<String> siteURLs = Arrays.asList(SITE_URL, EVICT_VARNISH_DESKTOP_SITE_URL, EVICT_VARNISH_MOBILE_SITE_URL);
		for(String s: siteURLs) {
			if(url.equalsIgnoreCase(s) || url.equalsIgnoreCase(s+"/")) {
				return true;
			}
		}
		return false;
	}
	private void evictFastlyCacheWithUrls(List<String> urlList, boolean evictVarnish) {
		//cleaning varnish cache
		if(evictVarnish) {
			evictVarnishCache(urlList);
		}
		logger.info("evicting fastly cache for urls:"  + urlList);
		for(String url:urlList){
			//httpUtil.purgeFastly(url);
			//logger.info("purgeResponse: " + purgeResponse);
		}
		// Map<String, String> headers = Collections.singletonMap("Fastly-Key", fastlyAccessKey);
		// for(String url: urlList) {
		// 	String purgeResponse = httpUtil.sendPurge(url, headers); 
		// 	logger.info("purgeResponse: " + purgeResponse);
		// }
	}
	private void evictVarnishCache(List<String> urlList) {
		URL uri = null;
		for(String url: urlList) {
			try {
				uri = new URL(url);
				String path = uri.getPath();
				logger.info("path : "+path );
				logger.info("paaths : " +EVICT_VARNISH_MOBILE_SITE_URL+path);
				//httpUtil.sendPurge(EVICT_VARNISH_DESKTOP_SITE_URL+path);
				//httpUtil.sendPurge(EVICT_VARNISH_MOBILE_SITE_URL+path);
			} catch (MalformedURLException e) {
				logger.error("Invalid url for cache eviction" + url);
			} 
		}
	}
}
