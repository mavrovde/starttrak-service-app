package com.starttrak.facebook;

import com.starttrak.common.SocNetwork;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.social.Facebook;
import com.starttrak.social.SocialNetworkClient;
import com.starttrak.social.SocialNetworkException;
import com.starttrak.social.SocialNetworkProfile;
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
public class FacebookAuthTextResponseServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(FacebookAuthTextResponseServlet.class);

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    @Facebook
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
//                    SocialNetworkProfile profile = networkClient.getProfileByAccessToken(accessToken);
//                    response.sendRedirect("http://mavrov.de:8080/starttrak-profiles-rest/service/profile?session_id=" +
//                            profileRepo.updateSocialProfile(
//                                    SocNetwork.FCBK, profile.getEmailAddress(),
//                                    profile.getFirstName(), profile.getLastName(),
//                                    profile.getPosition(), profile.getCompany(),
//                                    profile.getPictureUrl(),
//                                    profile.getCityName(),
//                                    profile.getRegion(),
//                                    profile.getCountry(),
//                                    accessToken));
                }
            } catch (SocialNetworkException e) {
                logger.error("some issue with social network was registered", e);
                throw new IllegalStateException(e);
            }
        }
    }

}
