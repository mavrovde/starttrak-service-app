package com.starttrak.xing;

import com.starttrak.repo.ProfileRepo;
import com.starttrak.social.Xing;
import com.starttrak.social.SocialNetworkClient;
import com.starttrak.social.SocialNetworkException;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author serg.mavrov@gmail.com
 */
public class XingAuthTextResponseServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(XingAuthTextResponseServlet.class);

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    @Xing
    private SocialNetworkClient networkClient;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if (code != null) {
            try {
                String accessToken = networkClient.getAccessToken(code,
                        "http://mavrov.de:8080/starttrak-facebook-login/facebook-auth-text-response");
                if (accessToken != null) {
                    response.setContentType("text/html");
                    // Actual logic goes here.
                    PrintWriter out = response.getWriter();
                    out.println("<h1>" + networkClient.getJsonProfileByAccessToken(accessToken) + "</h1>");
                    out.println("<h1>" + networkClient.getPhotoUrl(accessToken) + "</h1>");
                }
            } catch (SocialNetworkException e) {
                logger.error("some issue with social network was registered", e);
                throw new IllegalStateException(e);
            }
        }
    }

}
