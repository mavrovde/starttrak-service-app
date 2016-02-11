package com.starttrak.linkedin;

import com.starttrak.jpa.ProfileEntity;
import com.starttrak.jpa.UserEntity;
import com.starttrak.repo.NetworkRepo;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.repo.UserRepo;
import com.starttrak.social.Linkedin;
import com.starttrak.social.SocialNetworkClient;
import com.starttrak.social.SocialNetworkProfile;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinAuthResponseServlet extends HttpServlet {

    private final static long STRK_ID = 0;
    private final static long LNKD_ID = 1;

    @Inject
    private UserRepo userRepo;

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    private NetworkRepo networkRepo;

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
                        updateLinkedinProfile(profile, appKey));
            }
            // -=-=-
        }
    }

    private String updateLinkedinProfile(SocialNetworkProfile profile, String appKey) {
        Optional<ProfileEntity> linkedinProfile = profileRepo.findByEmailNetwork(LNKD_ID, profile.getEmailAddress());
        UserEntity user; // we are trying to define current user
        if (!linkedinProfile.isPresent()) { //there is no linkedin profile
            Optional<ProfileEntity> otherProfile = profileRepo.findByEmail(profile.getEmailAddress());
            if (!otherProfile.isPresent()) { //there is no any social profiles
                // create an user for starttrak
                user = userRepo.create(profile.getEmailAddress(), "empty_password", "registered by linkedin");
                // create the starttrak profile
                profileRepo.create(STRK_ID, profile.getEmailAddress(),
                        profile.getFirstName(), profile.getLastName(),
                        profile.getPosition(), profile.getCompany(),
                        user.getOwnSessionId(), user);
            } else {
                // there was found at least one social profile, so take an user
                user = otherProfile.get().getUser();
            }
            // create linkedin profile
            profileRepo.create(LNKD_ID, profile.getEmailAddress(),
                    profile.getFirstName(), profile.getLastName(),
                    profile.getPosition(), profile.getCompany(), appKey, user);
            // -=-=-=-
        } else { // we have already linkedin profile
            linkedinProfile.get().setNetworkToken(appKey);
            linkedinProfile.get().setLastLogin(new Date());
            profileRepo.update(linkedinProfile.get());
            user = linkedinProfile.get().getUser();
        }
        return user.getOwnSessionId();
    }

}
