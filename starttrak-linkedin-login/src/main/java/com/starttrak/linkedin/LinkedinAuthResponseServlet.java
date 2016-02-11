package com.starttrak.linkedin;

import com.starttrak.repo.ProfileRepo;
import com.starttrak.social.Linkedin;
import com.starttrak.social.SocialNetworkClient;
import com.starttrak.social.SocialNetworkProfile;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinAuthResponseServlet extends HttpServlet {

    private final static long LNKD_ID = 1;

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    @Linkedin
    private SocialNetworkClient networkClient;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if (code != null) {
            String appKey = networkClient.getAccessToken(code);
            //-=-=-=-
            if (appKey != null) {
                SocialNetworkProfile profile = networkClient.getProfileByAccessToken(appKey);
                response.sendRedirect("http://mavrov.de:8080/starttrak-profiles-rest/service/profile?session_id=" +
                        profileRepo.updateSocialProfile(LNKD_ID, profile.getEmailAddress(),
                                profile.getFirstName(), profile.getLastName(),
                                profile.getPosition(), profile.getCompany(), appKey));
            }
            // -=-=-
        }
    }

}
