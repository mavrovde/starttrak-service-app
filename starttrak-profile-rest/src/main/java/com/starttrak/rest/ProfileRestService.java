package com.starttrak.rest;

import com.starttrak.Ping;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.repo.ProfileRepo;
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

    @Inject
    private ProfileRepo profileRepo;

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
    public StandardResponse create(@HeaderParam("x-auth-id") String ownSessionId) {
        return new SuccessResponse<>(new ProfileEntity());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse get(@HeaderParam("x-auth-id") String hdrSessionId,
                                @QueryParam("session_id") String prmSessionId) {
        logger.info("the header found {x-auth-id:" + hdrSessionId + "}");
        logger.info("the parameter found {x-auth-id:" + prmSessionId + "}");
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