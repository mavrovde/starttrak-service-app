package com.starttrak.social;

/**
 * @author serg.mavrov@gmail.com
 */
public interface SocialNetworkClient {

    SocialNetworkProfile getProfileByAccessToken(String accessToken);

    SocialNetworkProfile getProfileByCode(String code);

    String getAccessToken(String code);
}
