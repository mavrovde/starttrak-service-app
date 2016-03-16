package com.starttrak.social;

import org.junit.Test;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.XingApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class PlainDataTest {

	@Test
    public void obtainData() {
        
    	String apiKey = "200498315bfcba1df1dc";
        String apiSecret = "c9d77b643643bd940c475ce08deba22c55dd3fa6";
    	
        // my auth token (Alexander Babin)
    	String oauthToken = "6d176908c2b9c1f402ba";
    	String oauthTikenSecret = "4d458965f9d42c2145be";
    	    	
        String callUrl = "https://api.xing.com/v1/users/me";
        
        OAuthService service = new ServiceBuilder()
        .provider(XingApi.class)
        .apiKey(apiKey)
        .apiSecret(apiSecret)
        .build();
                
                
        Token accessToken = new Token(oauthToken, oauthTikenSecret);  
        System.out.println("Access Token: " + accessToken);
                
        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest request = new OAuthRequest(Verb.GET, callUrl);
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getBody());
    }
    
}
