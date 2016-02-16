package com.starttrak.social;

import org.jboss.logging.Logger;

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
        return FacebookProfile.createNew(null, null, null,
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
        return accessToken;
    }

    @Override
    public String getAccessToken(String code) {
        return "facebook access code";
    }

}
