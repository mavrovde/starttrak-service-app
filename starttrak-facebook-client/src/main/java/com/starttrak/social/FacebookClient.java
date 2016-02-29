package com.starttrak.social;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.jboss.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@Facebook
@RequestScoped
public class FacebookClient implements SocialNetworkClient {

    private final static Logger logger = Logger.getLogger(FacebookClient.class);

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
        // -=-=-=-
        String firstName = jsonProfile.get("first_name").toString();
        String lastName = jsonProfile.get("last_name").toString();
        String emailAddress = jsonProfile.get("email").toString();
        return FacebookProfile.createNew(firstName, lastName, emailAddress,
                Optional.ofNullable(null),
                Optional.ofNullable(null)/*industry*/,
                Optional.ofNullable(null)/*seniority*/,
                Optional.ofNullable(null),
                Optional.ofNullable(null)/*sizes*/,
                Optional.ofNullable(null),
                Optional.ofNullable(null)/*city*/,
                Optional.ofNullable(null)/*region*/,
                Optional.ofNullable(null)/*country*/);
    }

    @Override
    public String getJsonProfileByAccessToken(String accessToken) throws SocialNetworkException {
        try {
            OAuthClientRequest bearerClientRequest =
                    new OAuthBearerClientRequest("https://graph.facebook.com/me?fields=id,first_name,last_name,email")
                            .setAccessToken(accessToken).buildQueryMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            return oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
                    OAuthResourceResponse.class).getBody();
        } catch (OAuthProblemException | OAuthSystemException e) {
            logger.error("some issue with getting/saving facebook profile", e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getAccessToken(String code) {
        try {
            OAuthClientRequest oauthRequest = OAuthClientRequest
                    .tokenProvider(OAuthProviderType.FACEBOOK)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId("659248597511389")
                    .setClientSecret("4e8d68b411affc73f0d15b82970b3056")
                    .setRedirectURI("http://mavrov.de:8080/starttrak-facebook-login/facebook-auth-response")
                    .setCode(code)
                    .buildBodyMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(oauthRequest, GitHubTokenResponse.class);
            logger.info("Access Token: " + oAuthResponse.getAccessToken() + ", Expires in: " +
                    oAuthResponse.getExpiresIn());
            return oAuthResponse.getAccessToken();
        } catch (OAuthSystemException | OAuthProblemException e) {
            logger.error("some error during facebook request", e);
            throw new IllegalStateException(e);
        }
    }

}
