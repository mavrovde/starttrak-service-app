package com.starttrak.social;

/**
 * @author serg.mavrov@gmail.com
 */
public interface SocialNetworkClient {

    SocialNetworkProfile getProfileByAccessToken(String accessToken) throws SocialNetworkException;

    String getJsonProfileByAccessToken(String accessToken) throws SocialNetworkException;

    String getPhotoUrl(String accessToken);

    String getAccessToken(String code, String redirectUrl);
}
