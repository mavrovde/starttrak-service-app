package com.starttrak.linkedin;

import com.starttrak.common.SocNetwork;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.social.Linkedin;
import com.starttrak.social.SocialNetworkClient;
import com.starttrak.social.SocialNetworkException;
import com.starttrak.social.SocialNetworkProfile;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinAuthResponseServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(LinkedinAuthResponseServlet.class);

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    @Linkedin
    private SocialNetworkClient networkClient;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if (code != null) {
            String accessToken = networkClient.getAccessToken(code);
            //-=-=-=-
            if (accessToken != null) {
                try {
                    SocialNetworkProfile profile = networkClient.getProfileByAccessToken(accessToken);
                    response.sendRedirect("http://mavrov.de:8080/starttrak-profiles-rest/service/profile?session_id=" +
                            profileRepo.updateSocialProfile(
                                    SocNetwork.LNKD, profile.getEmailAddress(),
                                    profile.getFirstName(), profile.getLastName(),
                                    profile.getPosition(), profile.getCompany(),
                                    profile.getPictureUrl(),
                                    profile.getCityName(),
                                    profile.getRegion(),
                                    profile.getCountry(),
                                    accessToken));
                } catch (SocialNetworkException e) {
                    logger.error("some issue with social network was registered", e);
                    throw new IllegalStateException(e);
                }
            }
            // -=-=-
        }
    }

}
