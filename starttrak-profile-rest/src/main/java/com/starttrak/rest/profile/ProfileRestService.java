package com.starttrak.rest.profile;

import com.starttrak.Ping;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.jpa.UserEntity;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.repo.UserRepo;
import com.starttrak.rest.request.ProfileCreateRequest;
import com.starttrak.rest.response.AuthErrorResponse;
import com.starttrak.rest.response.StandardResponse;
import com.starttrak.rest.response.SuccessResponse;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@Path("/profile")
@ApplicationScoped
public class ProfileRestService {

    private final static Logger logger = Logger.getLogger(ProfileRestService.class);
    private final static long STRK_ID = 0;

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    private UserRepo userRepo;

    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Ping getPing() {
        Ping ping = new Ping();
        ping.setMessage(new Date().toString());
        return ping;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse create(@HeaderParam("x-auth-id") String ownSessionId, ProfileCreateRequest request) {
        UserEntity user = userRepo.findByOwnSessionId(ownSessionId).orElseThrow(IllegalStateException::new);
        return new SuccessResponse<>(profileRepo.create(STRK_ID, request.getEmail(),
                request.getName(), request.getPhone(),
                request.getPositionId(),
                request.getCompanyLabel(), request.getCountryId(),
                request.getRegionId(), request.getSeniorityId(),
                request.getSizesId(), user.getOwnSessionId(), user));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse get(@HeaderParam("x-auth-id") String hdrSessionId,
                                @QueryParam("session_id") String prmSessionId) {
        logger.info("the header found {x-auth-id:" + hdrSessionId + "}");
        logger.info("the parameter found {x-auth-id:" + prmSessionId + "}");
        //so will be found the st profile since only it has public session id
        Optional<ProfileEntity> profile = profileRepo.findByOwnSessionId(hdrSessionId);
        if (!profile.isPresent()) {
            profile = profileRepo.findByOwnSessionId(prmSessionId);
        }
        if (profile.isPresent()) {
            logger.info("the profile = " + profile.get());
            return new SuccessResponse<>(profile.get());
        } else {
            return new AuthErrorResponse();
        }
    }

}