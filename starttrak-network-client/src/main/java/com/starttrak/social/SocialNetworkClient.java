package com.starttrak.social;

/**
 * @author serg.mavrov@gmail.com
 */
public interface SocialNetworkClient {

    SocialNetworkProfile getProfileByAccessToken(String accessToken) throws SocialNetworkException;

//    SocialNetworkProfile getProfileByCode(String code);

    String getAccessToken(String code);
}
