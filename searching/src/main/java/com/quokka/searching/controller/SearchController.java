package com.quokka.searching.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quokka.searching.entity.PlaFeed;
import com.quokka.searching.service.PlafeedService;

@RestController
@RequestMapping("/api/*")
public class SearchController {

	@Autowired
	PlafeedService plafeedService;

	@GetMapping("plaFeed")
	public String search(/*
								 * @RequestParam(required=false) Integer pageNumber,@RequestParam(required =
								 * false) Integer pageSize,
								 * 
								 * @RequestParam(required=false) Map<String,String> x,
								 * 
								 * @RequestParam(required=false)String msn,
								 * 
								 * @RequestParam(required=false)String gtin,
								 * 
								 * @RequestParam(required=false)String identifierExists,
								 * 
								 * @RequestParam(required=false)String customLabel2,
								 * 
								 * @RequestParam(required=false)String customLabel3,
								 * 
								 * @RequestParam(required=false)String customLabel4,
								 * 
								 * @RequestParam(required=false)String promotionId,
								 * 
								 * @RequestParam(required=false)String isGoogleActive,
								 * 
								 * @RequestParam(required=false)String isFacebookActive,
								 * 
								 * @RequestParam(required=false)String isCriteoActive,
								 * 
								 * @RequestParam(required=false)String availability
								 */
			
			@RequestParam(required = true)Map<String,Object> map) {

        System.out.println("*******"+map.keySet());
        /*  Map<String,Object> map =new HashMap<String,Object>();
          if(msn!=null)
          map.put("msn",msn);
          if(gtin!=null)
          map.put("gtin",gtin);
          if(identifierExists!=null)
          map.put("identifierExists",identifierExists);
          if(customLabel2!=null)
          map.put("customLabel2", customLabel2);
          if(customLabel3!=null)
          map.put("customLabel3", customLabel3);
          if(customLabel4!=null)
          map.put("customLabel4", customLabel4);
          if(promotionId!=null)
          map.put("promotionId", promotionId);
          if(isGoogleActive!=null)
          map.put("isGoogleActive", isGoogleActive);
          if(isCriteoActive!=null)
          map.put("isCriteoActive",isCriteoActive);
          if(isFacebookActive!=null)
          map.put("isFacebookActive", isFacebookActive);
          if(availability!=null)
          map.put("availability", availability);*/

          return plafeedService.searchWithMap( Integer.parseInt((String) map.get("pageNumber")),Integer.parseInt(((String) map.get("pageSize"))),map);
		//return plafeedService.search(pageNumber, pageSize, msn, gtin, identifierExists, customLabel2, customLabel3, customLabel4, promotionId, isGoogleActive, isFacebookActive, isCriteoActive, availability);
	}


}
