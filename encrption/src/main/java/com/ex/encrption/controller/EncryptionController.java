package com.ex.encrption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex.encrption.model.Token;
import com.ex.encrption.service.EncryptionService;

@RestController
@RequestMapping("/api/*")
public class EncryptionController {

	@Autowired
	private EncryptionService encryptionService;

	@GetMapping("encrypt")
	public String encrypt(@RequestParam String encrypt) {
		System.out.println("*********** " + encrypt + " "+encryptionService.encrypt(encrypt));
		String result =encryptionService.encrypt(encrypt);
		
		//lets decrpt the
		
		String decrypt=encryptionService.decrypt(result);
		System.out.println("******decrypt "+decrypt);
		// done
		return result;
	}

	@GetMapping("decrypt")
	public String decrypt(@RequestParam String decrypt) {

		return encryptionService.decrypt(decrypt);
	}
	@PostMapping("token")
	public String token(@RequestBody Token token) {
		return encryptionService.encode(token);
	}
	@GetMapping("decodeToken")
	public Token decodeToken(@RequestParam String decodeToken ) {
		return encryptionService.decode(decodeToken);
	}
}
