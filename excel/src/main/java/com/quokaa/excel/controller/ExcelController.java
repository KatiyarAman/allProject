package com.quokaa.excel.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quokaa.excel.entity.ExcelEntity;
import com.quokaa.excel.service.DiscoverService;
import com.quokaa.excel.service.ExcelService;

@RestController
@RequestMapping("/api/*")
public class ExcelController {

    private  static  final Logger logger= LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    private DiscoverService discoverService;

    @PostMapping("uploadFile")
    public String uploadExcel(@RequestParam("file")MultipartFile file){
        logger.info("Got the excel upload request with fileName "+file.getOriginalFilename());
        Pair<Boolean,String> response=excelService.save(file);
        if(response.getFirst())
            return response.getSecond();
        else
            return response.getSecond();
    }
    @GetMapping("/{id}")
    public JSONObject getbyId(@PathVariable("id")Long id,@RequestParam Map<String,String> param){
    	//logger.info("Got request for the ExcelEntity by Id : "+id + "param "+ param.entrySet().toString());
        ExcelEntity  execl=excelService.getById(id);
        
        String apiUrl="http://localhost:9090/plaFeed/list";
        String remoteResponse=discoverService.list(apiUrl, param);
        try {
        	String entity= objectMapper.writeValueAsString(execl);
        	return jsonObject(entity,remoteResponse);
        	
        }catch(JsonProcessingException e) {
        	return null;
        }
    }
    @GetMapping("")
    public List<ExcelEntity> list(){
        return excelService.list();
    }
     JSONObject jsonObject(String response,String remoteResponse) {
    	 JSONArray jsonArray1= new JSONArray();
    	 try {
			JSONArray jsonArray= (JSONArray)  new JSONParser().parse(remoteResponse);
			for(int i=0;i<jsonArray.size();i++) {
		    	 JSONObject data= new JSONObject();
				JSONObject object= (JSONObject) jsonArray.get(i);
				Long id = (Long)object.get("id");
				String msn= (String)object.get("msn");
				String customLabel4= (String)object.get("customLabel4");
				String customLabel2=(String)object.get("customLabel2");
				data.put("plaFeedId",id);
				data.put("msn",msn);
				data.put("customLabel4", customLabel4);
				data.put("customLabel2", customLabel2);
				jsonArray1.add(data);
			}
			JSONObject jsonObject= (JSONObject) new JSONParser().parse(response);
			
			jsonObject.put("plaFeed",jsonArray1);
			return jsonObject;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	 
    	 
    	 
     }

}
