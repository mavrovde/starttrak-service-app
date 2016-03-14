package com.starttrak.social.converter;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.starttrak.social.SocialNetworkException;
import com.starttrak.social.XingProfile;


/**
 * 
 * @author Alexander Babin
 * @version 1.0
 * @since 14.03.2016
 *
 */
@ApplicationScoped
public class XingJSONConverter {

	public XingProfile toProfile(String fullJsonString) throws SocialNetworkException{
		
		JSONObject userOb = extractUser(fullJsonString);
		    	
        String firstName    = String.valueOf(userOb.get("first_name"));
        String lastName     = String.valueOf(userOb.get("last_name"));
        String emailAddress = String.valueOf(userOb.get("active_email"));
        
        // position, industry, company
        Optional<String> position = Optional.ofNullable(null);
        Optional<String> industry = Optional.ofNullable(null);
        Optional<String> company = Optional.ofNullable(null);
        JSONObject profExpOb = (JSONObject) userOb.get("professional_experience");
        if(profExpOb != null && profExpOb.get("primary_company") != null){
        	JSONObject primaryCompanyOb = (JSONObject) profExpOb.get("primary_company");
        	position = Optional.ofNullable(getValue(primaryCompanyOb, "title"));
        	company = Optional.ofNullable(getValue(primaryCompanyOb, "name"));
        	
        	if(primaryCompanyOb.get("industries") != null){
        		JSONArray industriesOb = (JSONArray) primaryCompanyOb.get("industries");
        		if(industriesOb.size() > 0){
        			industry = Optional.ofNullable(getValue((JSONObject) industriesOb.get(0), "localized_name"));
        		}
        	}
        }
        
        // pictureURL
        Optional<String> pictureUrl = Optional.ofNullable(null);
        if(userOb.get("photo_urls") != null){
        	pictureUrl = Optional.ofNullable(getValue((JSONObject) userOb.get("photo_urls"), "large"));
        }
        
        // cityName, country , region
        Optional<String> cityName = Optional.ofNullable(null);
        Optional<String> country = Optional.ofNullable(null);
        Optional<String> region = Optional.ofNullable(null);
        if(userOb.get("private_address") !=  null){
        	JSONObject privateAddressOb = (JSONObject) userOb.get("private_address");
        	cityName = Optional.ofNullable(getValue(privateAddressOb,"city"));
        	country = Optional.ofNullable(getValue(privateAddressOb,"country"));
        	region = Optional.ofNullable(getValue(privateAddressOb,"province"));
        }
        
        XingProfile profile = new XingProfile.Builder()
						.firstName(firstName)
						.lastName(lastName)
						.emailAddress(emailAddress)
						.cityName(cityName)
						.company(company)
						.country(country)
						.industry(industry)
						.pictureUrl(pictureUrl)
						.position(position)
						.region(region)
						.seniority(Optional.ofNullable(null))
						.sizes(Optional.ofNullable(null))
						.build();
        
        return profile;
		
		
	}	
	
	public String getValue(JSONObject ob, String field){
		if(ob != null && ob.get(field) != null){
			return ob.get(field).toString();
		}
		return null;
	}
	
	private JSONObject extractUser(String strJson) throws SocialNetworkException{
    	try{
    		JSONObject ob = (JSONObject) JSONValue.parse(strJson);
        	JSONArray obUsers = (JSONArray) ob.get("users");
        	return (JSONObject) obUsers.get(0);
    	} catch(Exception e){
    		throw new SocialNetworkException("Error during parsing data: " + strJson , e);
    	}
		
    }
	
}
