package com.starttrak.rest.auth;

import com.starttrak.common.SocNetwork;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.jpa.UserEntity;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.repo.UserRepo;
import com.starttrak.rest.profile.LoginRequest;
import com.starttrak.rest.response.CodeErrorResponse;
import com.starttrak.rest.response.StandardResponse;
import com.starttrak.rest.response.SuccessResponse;
import com.starttrak.social.*;

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

    @Inject
    @Facebook
    private SocialNetworkClient facebookClient;
    
    @Inject
    @Xing
    private SocialNetworkClient xingClient;

    @GET
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<?> validate(@QueryParam("session_id") String sessionId) {
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
    public StandardResponse<?> login(LoginRequest regRequest) {
        switch (SocNetwork.findByCode(regRequest.getSocNetworkId())) {
            case STTR:	return processForStarttrak(regRequest);
            case LNKD:  return processForLinkedin(regRequest);
            case FCBK:  return processForFacebook(regRequest);
            case XING:  return processForXing(regRequest);
            
            default:    throw new IllegalStateException("no network login implemented");
        }
    }
    
    private StandardResponse<?> processForStarttrak(LoginRequest regRequest){
    	Optional<UserEntity> user = userRepo.findByEmail(regRequest.getEmail());
        if (user.isPresent()) {
            if (SocNetwork.STTR.getCode() != user.get().getSourceNetwork().getId().intValue()) {
                return new CodeErrorResponse(1001, "user exists (email is already taken)");
            }
            if (!regRequest.getPassword().equals(user.get().getPassword())) {
                return new CodeErrorResponse(1002, "an username/password is not correct");
            }
            Optional<ProfileEntity> profile = profileRepo.findByEmailNetwork(SocNetwork.STTR, regRequest.getEmail());
            return new SuccessResponse<>(new UserOwnSession(user.get().getOwnSessionId(), profile.isPresent()));
        } else {
            return new SuccessResponse<>(new UserOwnSession(userRepo.create(
                    (long) SocNetwork.STTR.getCode(),
                    regRequest.getEmail(),
                    regRequest.getPassword(),
                    "registered by starttrak"
            ).getOwnSessionId(), false));
        }
    }
    
    private StandardResponse<?> processForLinkedin(LoginRequest regRequest){
    	Optional<ProfileEntity> linkedin = profileRepo.findByEmailNetwork(SocNetwork.LNKD, regRequest.getEmail());
        if (linkedin.isPresent()) {
            return new SuccessResponse<>(new OwnSession(userRepo.findByEmail(
                    regRequest.getEmail()).orElseThrow(AuthenticationException::new
            ).getOwnSessionId()));
        } else {
            try {
                SocialNetworkProfile profile = linkedinClient.getProfileByAccessToken(
                        regRequest.getAccessToken());
                return new SuccessResponse<>(new OwnSession(
                        profileRepo.updateSocialProfile(SocNetwork.LNKD,
                                profile.getEmailAddress(),
                                profile.getFirstName(),
                                profile.getLastName(),
                                profile.getPosition(),
                                profile.getCompany(),
                                profile.getPictureUrl(),
                                profile.getCityName(),
                                profile.getRegion(),
                                profile.getCountry(),
                                regRequest.getAccessToken())));
            } catch (SocialNetworkException sne) {
                return new CodeErrorResponse(1003, "social network token issue");
            }
        }
    }
     
    private StandardResponse<?> processForFacebook(LoginRequest regRequest){
    	Optional<ProfileEntity> facebook = profileRepo.findByEmailNetwork(SocNetwork.FCBK, regRequest.getEmail());
        if (facebook.isPresent()) {
            return new SuccessResponse<>(new OwnSession(userRepo.findByEmail(
                    regRequest.getEmail()).orElseThrow(AuthenticationException::new
            ).getOwnSessionId()));
        } else {
            try {
                SocialNetworkProfile profile = facebookClient.getProfileByAccessToken(
                        regRequest.getAccessToken());
                return new SuccessResponse<>(new OwnSession(
                        profileRepo.updateSocialProfile(SocNetwork.FCBK,
                                profile.getEmailAddress(),
                                profile.getFirstName(),
                                profile.getLastName(),
                                profile.getPosition(),
                                profile.getCompany(),
                                profile.getPictureUrl(),
                                profile.getCityName(),
                                profile.getRegion(),
                                profile.getCountry(),
                                regRequest.getAccessToken())));
            } catch (SocialNetworkException sne) {
                return new CodeErrorResponse(1003, "social network token issue");
            }
        }
    }
    
    private StandardResponse<?> processForXing(LoginRequest regRequest){
    	Optional<ProfileEntity> xing = profileRepo.findByEmailNetwork(SocNetwork.XING, regRequest.getEmail());
        if (xing.isPresent()) {
            return new SuccessResponse<>(new OwnSession(userRepo.findByEmail(
                    regRequest.getEmail()).orElseThrow(AuthenticationException::new
            ).getOwnSessionId()));
        } else {
            try {
            	OauthToken accessToken = new OauthToken(regRequest.getOauthToken() , regRequest.getOauthTokenSecret());
            	SocialNetworkProfile profile = xingClient.getProfileByAccessToken(accessToken.serialize());
                return new SuccessResponse<>(new OwnSession(
                        profileRepo.updateSocialProfile(SocNetwork.XING,
                                profile.getEmailAddress(),
                                profile.getFirstName(),
                                profile.getLastName(),
                                profile.getPosition(),
                                profile.getCompany(),
                                profile.getPictureUrl(),
                                profile.getCityName(),
                                profile.getRegion(),
                                profile.getCountry(),
                                accessToken.serialize())));
            } catch (SocialNetworkException sne) {
                return new CodeErrorResponse(1003, "social network token issue");
            }
        }
    }

}
