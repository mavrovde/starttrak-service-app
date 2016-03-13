package com.starttrak.social;


/**
 * 
 * @author Alexander Babin
 * @version 1.0
 * @since 13.03.2015
 *
 */
public class OauthToken {
	
	public final String token;
	public final String secret;
	
	public OauthToken(String token, String secret){
		this.token = token;
		this.secret = secret;
	}
	
	public OauthToken(String fullToken){
		String [] parts = fullToken.split("&");
		this.token = parts[0];
		this.secret = parts[1];
	}
	
	public String serialize(){
		return token + "&" + secret; 
	}
	
	public String toString(){
		return getClass().getSimpleName() + ":{" + 
				"token=" + token + "; " +
				"secret=" + secret + 
				"}";
	}
}
