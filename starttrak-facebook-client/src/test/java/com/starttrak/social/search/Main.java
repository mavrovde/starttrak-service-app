package com.starttrak.social.search;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
	
	private static final String ACCESS_TOKEN = "CAAJXlUZA5LN0BABVchnthvwwnNXHuUCKf6oDbYoliLdcjns5S9E4ArJGZCVCLmLXhZByxLm1EcfvH6pfI2dXG3pV0IcHRrmfSTbBY8IelNhMjqflBUInIrsSTZCyKEoZCAMC7pNBWY0NlugAGPK8ZA2uZAh1bIKEdwhMApBZA5iSS0piJIJBcqNO6TUm9ZA2fmEkCUyuTKy5SkDLYjmR2ngbs";
	
	private static final String SEARCH_URL = "https://graph.facebook.com/v2.6/search?fields={fields}&type=user&q={criteria}&format=json&access_token={accessToken}&limit={pageSize}&offset=0"; 
	
	private static final String PROFILE_URL = "https://graph.facebook.com/v2.6/{id}?fields={fields}&format=json&access_token={accessToken}";
	
	//----------------------------------------------------
	private static final String CRITERIA = "Mavrov";
	private static final String PAGE_SIZE = "10";
	//-----------------------------------------------------
	
	private static final String FIELDS = "id,name,picture,about,age_range,bio,birthday,currency,devices,education,email,gender,first_name,hometown,last_name,link,locale,location,middle_name,updated_time,website,work";
	
	public static void main(String ... args){
		HttpRequester requester = new HttpRequester();
		Response resp = requester.request(prepareSearchUrl());
		
		System.out.println(resp.result);
		
		JSONObject ob = new JSONObject(resp.result);
		JSONArray ar = ob.getJSONArray("data");
				
		System.out.println("Count: " + ar.length() + ": ");
		for(int i = 0; i < ar.length(); i++){
			System.out.println(i + " - " + ar.getJSONObject(i).getString("name"));
			
			Response profileResp = new HttpRequester().request(prepareProfileUrl(ar.getJSONObject(i).getString("id")));
			System.out.println(i + " - PROFILE: " + profileResp.result);
		}
		
	}

	
	private static String prepareSearchUrl(){
		return SEARCH_URL
				.replace("{accessToken}", ACCESS_TOKEN)
				.replace("{pageSize}", PAGE_SIZE)
				.replace("{fields}", FIELDS)
				.replace("{criteria}", CRITERIA);
	}
	
	private static String prepareProfileUrl(String id){
		return PROFILE_URL
				.replace("{accessToken}", ACCESS_TOKEN)
				.replace("{fields}", FIELDS)
				.replace("{id}", id);
	}
	
}
