package com.starttrak.social.search;

import org.apache.commons.httpclient.Cookie;

public class Response {
	public final int code;
	public final String result;
	public final Cookie [] cookies;
	
	public Response(int code, String result, Cookie [] cookies){
		this.code = code;
		this.result = result;
		this.cookies = cookies;
	}
	
	public Response(int code){
		this.code = code;
		result = null;
		cookies = null;
	}
	
	public boolean isOK(){
		return code == 200;
	}
	
	 
}
