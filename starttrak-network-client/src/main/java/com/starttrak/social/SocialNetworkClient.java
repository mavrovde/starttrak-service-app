package com.starttrak.social;

/**
 * @author serg.mavrov@gmail.com
 */
public interface SocialNetworkClient {

    SocialNetworkProfile getProfileByAccessToken(String accessToken) throws SocialNetworkException;

    String getJsonProfileByAccessToken(String accessToken) throws SocialNetworkException;

    String getPhotoUrl(String accessToken) throws SocialNetworkException;

    String getAccessToken(String code, String redirectUrl) throws SocialNetworkException;
}
