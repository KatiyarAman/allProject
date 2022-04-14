package com.ex.encrption.service;

import com.ex.encrption.model.Token;

//encryption decryption using secrect key cipher
public interface EncryptionService {

	public String encrypt(String data);
	public String decrypt(String data);
	
	public String encode(Token token);
	public Token decode (String token);
}
