package com.starttrak.social.search;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequester {
	
	private static final Logger log = LoggerFactory.getLogger(HttpRequester.class);
	
	private static final int CONNECTION_TIMEOUT = 30000;
	
	private static CookieHolder cookieHolder = new HttpRequester.CookieHolder(); 
			
	public Response request(String url){
		return request(url , null);
	}
	
	public Response request(String url, Cookie [] cookies){
		
		HttpClient client = new HttpClient();
		
		// REQUEST
		//System.out.println("-----------------------------------------------------------------------------------------------------");
		//System.out.println("REQUEST:  " + url);
		
		try {
			url = encodeUrl(url);
			//log.debug("URL: {}" , url);
			GetMethod getMethod = new GetMethod(url);
			getMethod.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			setCookies(client);
			getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64)");
			client.getParams().setParameter("http.connection.timeout", CONNECTION_TIMEOUT);
			client.executeMethod(getMethod);
			holdCookies(getMethod);
			String resp = getMethod.getResponseBodyAsString();
			return new Response(200 , resp , null);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Error during request: {} ({})" , url , e.getMessage() , e);
			return new Response(0);
		}
	}

	private void setCookies(HttpClient client) {
		for(Cookie c : cookieHolder.getCookiesArray()){
			//System.out.println("Cookie: " + c.getName() + "=" + c.getValue());
		}
		client.getState().addCookies(cookieHolder.getCookiesArray());
	}

	private void holdCookies(GetMethod getMethod) {
				
		Header[] cookieHeaders = getMethod.getResponseHeaders("Set-Cookie");;
		
		/*
		for(Header h : cookieHeaders){
			HeaderElement el = h.getElements()[0];
			if("autoru_sid".equals(el.getName())){
				Cookie c = new Cookie();
				c.setName(el.getName());
				c.setValue(el.getValue());
				c.setDomain("auto.ru");
				
				
				try{
					c.setPath(h.getElements()[1].getParameterByName("path").getValue());
				}catch(Exception e){}
								
				c.setExpiryDate(new Date(System.currentTimeMillis() + 86400000L));
				cookiesList.add(c);
			}
		}
		*/
		for(Header h : cookieHeaders){
			HeaderElement el = h.getElements()[0];
			Cookie c = new Cookie();
			c.setName(el.getName());
			c.setValue(el.getValue());
			
			try {
				c.setDomain(getMethod.getURI().getHost());
			} catch (URIException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			String d = h.getElements()[1].getParameterByName("domain") == null ? null : h.getElements()[1].getParameterByName("domain").getValue();
			if(d!= null){
				if(d.startsWith(".")){
					c.setDomain(d.substring(1));
				}else{
					c.setDomain(d);
				}
			}
			*/
					
			
			try{
				c.setPath(h.getElements()[1].getParameterByName("path").getValue());
			}catch(Exception e){}
							
			c.setExpiryDate(new Date(System.currentTimeMillis() + 86400000L));
			
			printCookie(c);
			cookieHolder.setCookie(c);
			
		}
	}
	
	private void printCookie(Cookie c){
		//System.out.println("SET COOKIE: " + c.getName() + "=" + c.getValue() + "            " + "[domain=" + c.getDomain() + "; path=" + c.getPath() + "]");
	}
	
	public String encodeUrl(String str){
		return str.replace("[", "%5B").replace("]", "%5D").replace(",", "%2C").replace(" ", "+");
	}
	
	
	
	private static class CookieHolder{
		
		Set <Cookie> cookies = new HashSet<>();
		
		public void setCookie(Cookie cookie){
			Cookie find = findByName(cookie.getName());
			if(find != null){
				cookies.remove(find);
			}
			cookies.add(cookie);
		}
		
		private Cookie findByName(String name){
			for(Cookie c : cookies){
				if(c.getName().equals(name)){
					return c;
				}
			}
			return null;
		}
		
		public Cookie [] getCookiesArray(){
			Cookie [] ar = new Cookie[cookies.size()];
			return cookies.toArray(ar);
		}
		
		
	}
	
}
