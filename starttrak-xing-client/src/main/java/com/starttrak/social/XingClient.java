package com.starttrak.social;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.XingApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * @author serg.mavrov@gmail.com
 */
@Xing
@ApplicationScoped
public class XingClient implements SocialNetworkClient {

    private final Logger log = Logger.getLogger(XingClient.class);
    
    private final String API_KEY    = "200498315bfcba1df1dc";
    private final String API_SECRET = "c9d77b643643bd940c475ce08deba22c55dd3fa6";
    private final String CALL_URL   = "https://api.xing.com/v1/users/me";

    /*
{"users":[
   {
    "id":"23180824_f3367b",
    "active_email":"serg.mavrov@gmail.com",
    "time_zone":{"utc_offset":1.0,"name":"Europe/Berlin"},
    "display_name":"Sergii Mavrov",
    "academic_title":null,
    "first_name":"Sergii",
    "last_name":"Mavrov",
    "gender":"m",
    "page_name":"Sergii_Mavrov",
	"birth_date":{"year":1979,"month":6,"day":29},
	"wants":null,
	"haves":"Java, JavaEE, Tomcat, Wildfly, CDI, EJB, SQL, Webservices, SOAP, REST, Java Spring Framework, XML, Hibernate-Framework",
 	"top_haves":null,"interests":null,
 	"web_profiles":{},
 	"badges":[],
 	"legal_information":{"preview_content":null},
 	"photo_urls":{"large":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.140x185.jpg",
 		"maxi_thumb":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.70x93.jpg",
 		"medium_thumb":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.57x75.jpg",
 		"mini_thumb":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.18x24.jpg",
 		"thumb":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.30x40.jpg",
 		"size_32x32":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.32x32.jpg",
 		"size_48x48":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.48x48.jpg",
 		"size_64x64":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.64x64.jpg",
	 	"size_96x96":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.96x96.jpg",
	 	"size_128x128":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.128x128.jpg",
	 	"size_192x192":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.192x192.jpg",
	 	"size_256x256":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.256x256.jpg",
	 	"size_1024x1024":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.1024x1024.jpg",
	 	"size_original":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.original.jpg"
	 },
 	"permalink":"https://www.xing.com/profile/Sergii_Mavrov",
 	"languages":{"de":"BASIC","en":"FLUENT","ru":"NATIVE"},
 	"employment_status":"EMPLOYEE",
 	"organisation_member":null,
 	"instant_messaging_accounts":{"skype":"serg.mavrov", "googletalk":"serg.mavrov@gmail.com"},
 	"educational_background":{
 	                      "degree":null,
 	                      "primary_school":null, 
 	                      "schools":[
 	                                   {"name":"Tiraspol State University, Moldova",
 	                                   "subject":"Mathematics and Computer Science",
                                       "degree":"Specialist",
                                       "begin_date":"1997-09",
                                       "end_date":"2002-06",
                                       "notes":null,
                                       "id":"1_253d4e"
                                       }
                           ],
 	"qualifications":[]},
 	"professional_experience":{
 	                           "primary_company":{
 	                                            "id":"36250391_ff54f5",
 	                                            "name":"TravelTainment GmbH",
 	                                            "url":null,
                                                "tag":"TRAVELTAINMENTGMBH",
                                                "title":"Java Senior Developer",
                                                "begin_date":null,
                                                "end_date":null,
                                                "description":null,
                                                "discipline":null,
                                                "until_now":true,
                                                "industry":"INFORMATION_TECHNOLOGY_AND_SERVICES",
                                                "industries":[{"id":90000,"localized_name":"Internet and IT"}],
                                                "company_size":null,
                                                "career_level":null,
                                                "form_of_employment":"FULL_TIME_EMPLOYEE"
                                              },
                               "companies":[{"id":"36250391_ff54f5","name":"TravelTainment GmbH",
 "url":null,"tag":"TRAVELTAINMENTGMBH","title":"Java Senior Developer","begin_date":null,"end_date":null,
 "description":null,"discipline":null,"until_now":true,"industry":"INFORMATION_TECHNOLOGY_AND_SERVICES",
 "industries":[{"id":90000,"localized_name":"Internet and IT"}],"company_size":null,"career_level":null,
 "form_of_employment":"FULL_TIME_EMPLOYEE"}],
 
                                "awards":[],
                                "non_primary_companies":[]
   },
   
 "private_address":{
                    "street":null, 
                    "zip_code":null,
                    "city":null,
                    "province":null,
                    "country":null,
                    "email":null,
                    "fax":null,
                    "phone":null,
                    "mobile_phone":null
  },
 "business_address":{
                    "street":"Carlo-Schmid-Str. 12",
                    "zip_code":"52146",
                    "city":"Aachen",
                    "province":"Nordrhein-Westfalen",
                    "country":"DE",
                    "email":"serg.mavrov@gmail.com",
                    "fax":null,
                    "phone":null,
                    "mobile_phone":"49|1573|3699427"
   },
   "premium_services":[]
   
}]}
     */
    @Override
    public SocialNetworkProfile getProfileByAccessToken(String accessToken) throws SocialNetworkException {
    	String fullJsonString = getJsonProfileByAccessToken(accessToken);
    	JSONObject userOb;
		try {
			userOb = extractUser(fullJsonString);
		} catch (ParseException e) {
			throw new SocialNetworkException("Error during parsing data: " + fullJsonString , e);
		}
    	
        String firstName = String.valueOf(userOb.get("first_name"));
        String lastName = String.valueOf(userOb.get("last_name"));
        String emailAddress = String.valueOf(userOb.get("active_email"));
        
        // position, industry, company
        Optional<String> position = Optional.ofNullable(null);
        Optional<String> industry = Optional.ofNullable(null);
        Optional<String> company = Optional.ofNullable(null);
        JSONObject profExpOb = (JSONObject) userOb.get("professional_experience");
        if(profExpOb != null && profExpOb.get("primary_company") != null){
        	JSONObject primaryCompanyOb = (JSONObject) profExpOb.get("primary_company");
        	if(primaryCompanyOb.get("title") != null){
        		position = Optional.ofNullable(primaryCompanyOb.get("title").toString());
        	}
        	if(primaryCompanyOb.get("name") != null){
        		company = Optional.ofNullable(primaryCompanyOb.get("name").toString());
        	}
        	if(primaryCompanyOb.get("industries") != null){
        		JSONArray industriesOb = (JSONArray) primaryCompanyOb.get("industries");
        		if(industriesOb.size() > 0){
        			JSONObject industryOb = (JSONObject) industriesOb.get(0);
        			industry = Optional.ofNullable(industryOb.get("localized_name").toString());
        		}
        	}
        }
        
        // pictureURL
        Optional<String> pictureUrl = Optional.ofNullable(null);
        if(userOb.get("photo_urls") != null){
        	JSONObject photosOb = (JSONObject) userOb.get("photo_urls");
        	if(photosOb.get("large") != null){
        		pictureUrl = Optional.ofNullable(photosOb.get("large").toString());
        	}
        }
        
        // cityName, country , region
        Optional<String> cityName = Optional.ofNullable(null);
        Optional<String> country = Optional.ofNullable(null);
        Optional<String> region = Optional.ofNullable(null);
        if(userOb.get("private_address") !=  null){
        	JSONObject privateAddressOb = (JSONObject) userOb.get("private_address");
        	if(privateAddressOb.get("city") != null){
        		cityName = Optional.ofNullable(privateAddressOb.get("city").toString());
        	}
        	if(privateAddressOb.get("country") != null){
        		country = Optional.ofNullable(privateAddressOb.get("country").toString());
        	}
        	if(privateAddressOb.get("province") != null){
        		country = Optional.ofNullable(privateAddressOb.get("province").toString());
        	}
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
    
    

    @Override
    public String getJsonProfileByAccessToken(String accessToken) throws SocialNetworkException {
    	    	
    	OAuthService service = new ServiceBuilder()
        							.provider(XingApi.class)
        							.apiKey(API_KEY)
        							.apiSecret(API_SECRET)
        							.build();
    	
    	OauthToken oauthToken = new OauthToken(accessToken);
    	
    	Token token = new Token(oauthToken.token, oauthToken.secret);  
        
        OAuthRequest request = new OAuthRequest(Verb.GET, CALL_URL);
        service.signRequest(token, request);
        Response response = request.send();
        
        return response.getBody();
        
    }
    
    private JSONObject extractUser(String strJson) throws ParseException{
    	JSONObject ob = (JSONObject) JSONValue.parse(strJson);
    	JSONArray obUsers = (JSONArray) ob.get("users");
    	return (JSONObject) obUsers.get(0);
    }

    @Override
    public String getPhotoUrl(String accessToken) throws SocialNetworkException {
        throw new SocialNetworkException("Not eat implemented !");
    	
    }

    @Override
    public String getAccessToken(String code, String url) throws SocialNetworkException {
    	throw new SocialNetworkException("Not eat implemented !");
    }

}
