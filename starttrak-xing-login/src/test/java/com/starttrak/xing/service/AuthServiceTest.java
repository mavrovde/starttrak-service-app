package com.starttrak.xing.service;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.starttrak.xing.BaseTest;


public class AuthServiceTest extends BaseTest{
		
	@Inject
    private AuthService service;
	
	@Test
	@Ignore
	public void testHash() throws ParseException{
		JSONObject ob = prepareJSON();				
		boolean f = service.check(ob);
		Assert.assertTrue(f);
	}
	
	private JSONObject prepareJSON() throws ParseException{
		String userString = "{\"id\":\"24556836_4a0966\",\"first_name\":\"Alexander\",\"last_name\":\"Babin\",\"display_name\":\"Alexander Babin\",\"active_email\":\"alexander.babin@gmail.com\",\"permalink\":\"https://www.xing.com/profile/Alexander_Babin2\",\"business_address\":{\"street\":null,\"city\":\"Moscow\",\"province\":\"Moscow City\",\"country\":\"RU\"},\"photo_urls\":{\"maxi_thumb\":\"https://www.xing.com/image/1_e_2_b9c9aedee_24556836_1/alexander-babin-foto.70x93.jpg\"},\"professional_experience\":{\"primary_company\":{\"name\":\"CTI\",\"title\":\"Руководитель группы разработки операторских решений\",\"industry\":\"INFORMATION_TECHNOLOGY_AND_SERVICES\"}},\"signature\":\"238d26d56d05b90c370ec8e7ea3507c6d5a7b8e2b8fc2e8f3749608df266808e\"}";
		//String userString = "{\"id\":\"24556836_4a0966\",\"first_name\":\"Alexander\",\"last_name\":\"Babin\",\"display_name\":\"Alexander Babin\",\"active_email\":\"alexander.babin@gmail.com\",\"permalink\":\"https://www.xing.com/profile/Alexander_Babin2\",\"business_address\":{\"street\":\"\",\"city\":\"Moscow\",\"province\":\"Moscow City\",\"country\":\"RU\"},\"photo_urls\":{\"maxi_thumb\":\"https://www.xing.com/image/1_e_2_b9c9aedee_24556836_1/alexander-babin-foto.70x93.jpg\"},\"professional_experience\":{\"primary_company\":{\"name\":\"CTI\",\"title\":\"Руководитель группы разработки операторских решений\",\"industry\":\"INFORMATION_TECHNOLOGY_AND_SERVICES\"}},\"signature\":\"238d26d56d05b90c370ec8e7ea3507c6d5a7b8e2b8fc2e8f3749608df266808e\"}";
		//String userString = "{\"id\":\"24556836_4a0966\",\"first_name\":\"Alexander\",\"last_name\":\"Babin\",\"display_name\":\"Alexander Babin\",\"active_email\":\"alexander.babin@gmail.com\",\"permalink\":\"https://www.xing.com/profile/Alexander_Babin2\",\"business_address\":{\"street\":\"null\",\"city\":\"Moscow\",\"province\":\"Moscow City\",\"country\":\"RU\"},\"photo_urls\":{\"maxi_thumb\":\"https://www.xing.com/image/1_e_2_b9c9aedee_24556836_1/alexander-babin-foto.70x93.jpg\"},\"professional_experience\":{\"primary_company\":{\"name\":\"CTI\",\"title\":\"Руководитель группы разработки операторских решений\",\"industry\":\"INFORMATION_TECHNOLOGY_AND_SERVICES\"}},\"signature\":\"238d26d56d05b90c370ec8e7ea3507c6d5a7b8e2b8fc2e8f3749608df266808e\"}";
		return (JSONObject) new JSONParser().parse(userString);
	}
	
	
}
