package com.starttrak.xing.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.codec.binary.Hex;



@ApplicationScoped
public class Hmac256Service extends BaseService{
	
	public String prepareHash(String userData, String salt){
		try {
			
			SecretKeySpec secretKeySpec = new SecretKeySpec(salt.getBytes(), "HmacSHA256");
			
			Mac sha256HMAC = Mac.getInstance("HmacSHA256");
			sha256HMAC.init(secretKeySpec);
			
			byte [] b = sha256HMAC.doFinal(userData.getBytes());
			System.out.println(b);
			String hash = Hex.encodeHexString( sha256HMAC.doFinal(userData.getBytes()) ) ;
			return hash;
		} catch (Exception e) {
			log.error("Error during apply HMAC_SHA256 hash!" , e);
		}

		return null;
	}
	
}
