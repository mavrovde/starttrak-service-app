package com.starttrak.rest.auth;

import com.starttrak.common.SocNetwork;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.jpa.UserEntity;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.repo.UserRepo;
import com.starttrak.rest.request.RegRequest;
import com.starttrak.rest.response.CodeErrorResponse;
import com.starttrak.rest.response.StandardResponse;
import com.starttrak.rest.response.SuccessResponse;
import com.starttrak.social.Linkedin;
import com.starttrak.social.SocialNetworkClient;
import com.starttrak.social.SocialNetworkException;
import com.starttrak.social.SocialNetworkProfile;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@Path("/auth")
@ApplicationScoped
public class AuthRestService {

    @Inject
    private UserRepo userRepo;

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    @Linkedin
    private SocialNetworkClient linkedinClient;

    @GET
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse validate(@QueryParam("session_id") String sessionId) {
        Optional<UserEntity> user = userRepo.findByOwnSessionId(sessionId);
        if (user.isPresent()) {
            return new SuccessResponse<>(new OwnSession(user.get().getOwnSessionId()));
        } else {
            return new CodeErrorResponse(1007, "the session does not exists");
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse login(RegRequest regRequest) {
        switch (SocNetwork.findByCode(regRequest.getSocNetworkId())) {
            case STTR: //starttrak
                Optional<UserEntity> user = userRepo.findByEmail(regRequest.getEmail());
                if (user.isPresent()) {
                    if (SocNetwork.STTR.getCode() != user.get().getSourceNetwork().getId().intValue()) {
                        return new CodeErrorResponse(1001, "user exists (email is already taken)");
                    }
                    if (!regRequest.getPassword().equals(user.get().getPassword())) {
                        return new CodeErrorResponse(1002, "an username/password is not correct");
                    }
                    return new SuccessResponse<>(new OwnSession(user.get().getOwnSessionId()));
                } else {
                    return new SuccessResponse<>(new OwnSession(userRepo.create(
                            (long) SocNetwork.STTR.getCode(),
                            regRequest.getEmail(),
                            regRequest.getPassword(),
                            "registered by starttrak"
                    ).getOwnSessionId()));
                }
            case LNKD: //linkedin
                Optional<ProfileEntity> linkedin = profileRepo.findByEmailNetwork(
                        (long) SocNetwork.LNKD.getCode(), regRequest.getEmail());
                if (linkedin.isPresent()) {
                    return new SuccessResponse<>(new OwnSession(userRepo.findByEmail(
                            regRequest.getEmail()).orElseThrow(AuthenticationException::new
                    ).getOwnSessionId()));
                } else {
                    try {
                        SocialNetworkProfile profile = linkedinClient.getProfileByAccessToken(regRequest.getAccessToken());
                        return new SuccessResponse<>(new OwnSession(
                                profileRepo.updateSocialProfile(SocNetwork.LNKD, profile.getEmailAddress(),
                                        profile.getFirstName(), profile.getLastName(),
                                        profile.getPosition(), profile.getCompany(),
                                        regRequest.getAccessToken())));
                    } catch (SocialNetworkException sne) {
                        return new CodeErrorResponse(1006, "social network token issue");
                    }
                }
            case FCBK: //facebook
                throw new IllegalStateException("no network login implemented");
            case XING: //xing
                throw new IllegalStateException("no network login implemented");
            default:
                throw new IllegalStateException("no network login implemented");
        }
    }

}
