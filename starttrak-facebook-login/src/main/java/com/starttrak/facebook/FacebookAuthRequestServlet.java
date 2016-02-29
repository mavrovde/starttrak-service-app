package com.starttrak.facebook;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.jboss.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author serg.mavrov@gmail.com
 */
public class FacebookAuthRequestServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(FacebookAuthRequestServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            OAuthClientRequest oauthRequest = OAuthClientRequest
                    .authorizationProvider(OAuthProviderType.FACEBOOK)
                    .setClientId("659248597511389")
                    .setRedirectURI("http://mavrov.de:8080/starttrak-facebook-login/facebook-auth-response")
                    .buildQueryMessage();
            //redirect to the linkedin site
            response.sendRedirect(oauthRequest.getLocationUri());
        } catch (OAuthSystemException e) {
            logger.error("some error during facebook registration", e);
            throw new IllegalStateException(e);
        }
    }

}
