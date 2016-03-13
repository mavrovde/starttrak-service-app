package com.starttrak.xing.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;


@ApplicationScoped
public class AuthService {
	
	private final String SALT = "b8e72ffa2f9181edfbe918b068bfa5994f6d1505";
	
	@Inject private Hmac256Service hasher; 
	
	public boolean check(JSONObject ob){
		
		// 1. make array of strings
		List <String> list = convertJsonToList(ob);
		
		String[] ar = list.toArray(new String[list.size()]);
		// 2. sort alphabetically
		Arrays.sort(ar);
		
		// 3.convert to string
		String userData = StringUtils.join(ar);
		
		// 4. Create a HMAC SHA256
		String hash = hasher.prepareHash(userData, SALT);
				
		System.out.println(hash);
		
		return false;
	}	
			
	private List<String> convertJsonToList(JSONObject ob){
		return convertJsonToList("", ob);
	}
	
	private List<String> convertJsonToList(String keyPrefix , JSONObject ob){
		List <String> ar = new ArrayList<>();
		for(Object key : ob.keySet()){
			Object v = ob.get(key);
			if(v == null){
				ar.add(keyPrefix+ key);
			} else if(v instanceof JSONObject){
				ar.addAll(convertJsonToList(keyPrefix + key , (JSONObject)v));
			}else{
				ar.add(keyPrefix+ key + v);
			}
		}
		return ar;
	}
 	
	
	
}
