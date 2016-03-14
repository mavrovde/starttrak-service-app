package com.starttrak.social;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.XingApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.starttrak.social.converter.XingJSONConverter;

/**
 * @author serg.mavrov@gmail.com
 */
@Xing
@ApplicationScoped
public class XingClient implements SocialNetworkClient {
		
    private final Logger log = Logger.getLogger(this.getClass());
    
    private final String API_KEY    = "200498315bfcba1df1dc";
    private final String API_SECRET = "c9d77b643643bd940c475ce08deba22c55dd3fa6";
    private final String CALL_URL   = "https://api.xing.com/v1/users/me";

    @Inject 
    private XingJSONConverter converter;
    
    @Override
    public SocialNetworkProfile getProfileByAccessToken(String accessToken) throws SocialNetworkException {
    	log.info("Requesting profile by accessToken: " + accessToken + " ... ");
    	String fullJsonString = getJsonProfileByAccessToken(accessToken);
    	log.info("Result for [" + accessToken + "] : " + fullJsonString);
    	return converter.toProfile(fullJsonString);
    }

    @Override
    public String getJsonProfileByAccessToken(String accessToken) throws SocialNetworkException {
    	    	
    	OAuthService service = createOauthService(); 
    	OauthToken oauthToken = new OauthToken(accessToken);
    	Token token = new Token(oauthToken.token, oauthToken.secret);  
        
        OAuthRequest request = new OAuthRequest(Verb.GET, CALL_URL);
        service.signRequest(token, request);
        
        return request.send().getBody();
    }
        
    @Override
    public String getPhotoUrl(String accessToken) throws SocialNetworkException {
        throw new SocialNetworkException("Not eat implemented !");
    	
    }

    @Override
    public String getAccessToken(String code, String url) throws SocialNetworkException {
    	throw new SocialNetworkException("Not eat implemented !");
    }

    private OAuthService createOauthService(){
    	return new ServiceBuilder()
					.provider(XingApi.class)
					.apiKey(API_KEY)
					.apiSecret(API_SECRET)
					.build();
    }
    
}
