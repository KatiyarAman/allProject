package com.quokka.csvupload.controller;

import com.quokka.csvupload.config.SendSMS;
import com.quokka.csvupload.entities.PlaFeed;
import com.quokka.csvupload.service.PlaFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.quokka.csvupload.service.FileUploadingService;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/*")
public class FileUploadingController {

	@Autowired
	private FileUploadingService fileUploadingService;

	@Autowired
	private PlaFeedService plaFeedService;
	@Autowired
	SendSMS sendSMS;
	
	@PostMapping("uploadFile")
	public String fileUpload(@RequestParam("file") MultipartFile file) {
		return fileUploadingService.fileUploading(file);
	}

	@GetMapping("sendSms")
	public String sendSms(@RequestParam("text") String otp,@RequestParam("number")Long number){
		return 	sendSMS.sendSms("Your one time verification code for shivit miniERP is : "+otp, number.toString());

	}
	@GetMapping("getPlaFeedList")
	public List<PlaFeed> getPlaFeedList(@RequestParam Map<String,Object> map){
		try{
		return 	plaFeedService.getPlaFeedList(map);
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}
 }
