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
@Xing
@RequestScoped
public class XingClient implements SocialNetworkClient {

    private final static Logger logger = Logger.getLogger(XingClient.class);

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
