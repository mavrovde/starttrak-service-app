package com.starttrak.social;

import org.jboss.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@Xing
@RequestScoped
public class XingClient implements SocialNetworkClient {

    private final static Logger logger = Logger.getLogger(XingClient.class);

    /*
{"users":[{"id":"23180824_f3367b","active_email":"serg.mavrov@gmail.com",
"time_zone":{"utc_offset":1.0,"name":"Europe/Berlin"},"display_name":"Sergii Mavrov",
"academic_title":null,"first_name":"Sergii","last_name":"Mavrov","gender":"m","page_name":"Sergii_Mavrov",
"birth_date":{"year":1979,"month":6,"day":29},"wants":null,"haves":"Java, JavaEE, Tomcat, Wildfly, CDI, EJB, SQL,
 Webservices, SOAP, REST, Java Spring Framework, XML, Hibernate-Framework","top_haves":null,"interests":null,
 "web_profiles":{},"badges":[],"legal_information":{"preview_content":null},
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
 "size_original":"https://www.xing.com/img/users/1/3/f/bea6c3bd6.23180824,1.original.jpg"},
 "permalink":"https://www.xing.com/profile/Sergii_Mavrov","languages":{"de":"BASIC","en":"FLUENT","ru":"NATIVE"},
 "employment_status":"EMPLOYEE","organisation_member":null,"instant_messaging_accounts":{"skype":"serg.mavrov",
 "googletalk":"serg.mavrov@gmail.com"},"educational_background":{"degree":null,"primary_school":null,
 "schools":[{"name":"Tiraspol State University, Moldova","subject":"Mathematics and Computer Science",
 "degree":"Specialist","begin_date":"1997-09","end_date":"2002-06","notes":null,"id":"1_253d4e"}],"qualifications":[]},"
 professional_experience":{"primary_company":{"id":"36250391_ff54f5","name":"TravelTainment GmbH","url":null,
 "tag":"TRAVELTAINMENTGMBH","title":"Java Senior Developer","begin_date":null,"end_date":null,"description":null,
 "discipline":null,"until_now":true,"industry":"INFORMATION_TECHNOLOGY_AND_SERVICES",
 "industries":[{"id":90000,"localized_name":"Internet and IT"}],"company_size":null,"career_level":null,
 "form_of_employment":"FULL_TIME_EMPLOYEE"},"companies":[{"id":"36250391_ff54f5","name":"TravelTainment GmbH",
 "url":null,"tag":"TRAVELTAINMENTGMBH","title":"Java Senior Developer","begin_date":null,"end_date":null,
 "description":null,"discipline":null,"until_now":true,"industry":"INFORMATION_TECHNOLOGY_AND_SERVICES",
 "industries":[{"id":90000,"localized_name":"Internet and IT"}],"company_size":null,"career_level":null,
 "form_of_employment":"FULL_TIME_EMPLOYEE"}],"awards":[],"non_primary_companies":[]},"private_address":{"street":null,
 "zip_code":null,"city":null,"province":null,"country":null,"email":null,"fax":null,"phone":null,"mobile_phone":null},
 "business_address":{"street":"Carlo-Schmid-Str. 12","zip_code":"52146","city":"Aachen",
 "province":"Nordrhein-Westfalen","country":"DE","email":"serg.mavrov@gmail.com","fax":null,"phone":null,
 "mobile_phone":"49|1573|3699427"},"premium_services":[]}]}
     */
    @Override
    public SocialNetworkProfile getProfileByAccessToken(String accessToken) throws SocialNetworkException {
        String textProfile = getJsonProfileByAccessToken(accessToken);
//        {
//                "id": "1395229297457615",
//                    "first_name": "Sergii",
//                    "last_name": "Mavrov",
//                    "email": "serg.mavrov@icloud.com"
//        }
        Object parsedProfile = JSONValue.parse(textProfile);
        JSONObject jsonProfile = (JSONObject) parsedProfile;
        String firstName = jsonProfile.get("first_name").toString();
        String lastName = jsonProfile.get("last_name").toString();
        String emailAddress = jsonProfile.get("email").toString();

        final String[] cityName = {null};
        final String[] countryName = {null};
        Optional<Object> jsonLocation = Optional.ofNullable(jsonProfile.get("location"));
        jsonLocation.ifPresent(value -> {
                    JSONObject parsedLocation = (JSONObject) JSONValue.parse(value.toString());
                    String finalLocation = parsedLocation.get("name").toString();
                    if (finalLocation != null) {
                        String[] loc = finalLocation.split(",");
                        cityName[0] = loc[0];
                        countryName[0] = loc[1];
                    }
                }
        );
        String pictureUrl = getPhotoUrl(accessToken);
        return XingProfile.createNew(firstName, lastName, emailAddress,
                Optional.ofNullable(null),
                Optional.ofNullable(null)/*industry*/,
                Optional.ofNullable(null)/*seniority*/,
                Optional.ofNullable(null),
                Optional.ofNullable(null)/*sizes*/,
                Optional.ofNullable(pictureUrl), /*photo*/
                Optional.ofNullable(cityName[0])/*city*/,
                Optional.ofNullable(null)/*region*/,
                Optional.ofNullable(countryName[0])/*country*/);
    }

    @Override
    public String getJsonProfileByAccessToken(String accessToken) throws SocialNetworkException {
        try {
            OAuthClientRequest bearerClientRequest =
                    new OAuthBearerClientRequest("https://graph.facebook.com/me?fields=id,first_name,last_name,email,location")
                            .setAccessToken(accessToken).buildQueryMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            return oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
                    OAuthResourceResponse.class).getBody();
        } catch (OAuthProblemException | OAuthSystemException e) {
            logger.error("some issue with getting/saving facebook profile", e);
            throw new SocialNetworkException("facebook json profile", e);
        }
    }

    @Override
    public String getPhotoUrl(String accessToken) throws SocialNetworkException {
        try {
            OAuthClientRequest bearerClientRequest =
                    new OAuthBearerClientRequest("https://graph.facebook.com/me/picture?type=large&redirect=false")
                            .setAccessToken(accessToken).buildQueryMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            String response = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
                    OAuthResourceResponse.class).getBody();
            logger.info("facebook photo response -> " + response);
            /*
            {
                "data": {
                    "is_silhouette": false,
                    "url": "https://scontent.xx.fbcdn.net/hprofile-xft1/v/t1.0-1/p200x200/1929950_1539026919744518_557623504237816364_n.jpg?oh=c623e205aa064ad72c82992671fad8f4&oe=574B2F1D"
                }
            }
             */
            JSONObject jsonResponse = (JSONObject) JSONValue.parse(response);
            Optional<Object> data = Optional.ofNullable(jsonResponse.get("data"));
            final String[] pictureUrl = {null};
            data.ifPresent(value -> {
                        Object parsedData = JSONValue.parse(value.toString());
                        JSONObject jsonData = (JSONObject) parsedData;
                        pictureUrl[0] = jsonData.get("url").toString();
                    }
            );
            return pictureUrl[0];
        } catch (OAuthProblemException | OAuthSystemException e) {
            logger.error("some issue with getting/saving facebook photo", e);
            throw new SocialNetworkException("facebook photo url", e);
        }
    }

    @Override
    public String getAccessToken(String code, String url) throws SocialNetworkException {
        try {
            OAuthClientRequest oauthRequest = OAuthClientRequest
                    .tokenProvider(OAuthProviderType.FACEBOOK)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId("659248597511389")
                    .setClientSecret("4e8d68b411affc73f0d15b82970b3056")
                    .setRedirectURI(url)
                    .setCode(code)
                    .buildBodyMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(oauthRequest, GitHubTokenResponse.class);
            logger.info("Access Token: " + oAuthResponse.getAccessToken() + ", Expires in: " +
                    oAuthResponse.getExpiresIn());
            return oAuthResponse.getAccessToken();
        } catch (OAuthSystemException | OAuthProblemException e) {
            logger.error("some error during facebook request", e);
            throw new SocialNetworkException("facebook access token", e);
        }
    }

}
