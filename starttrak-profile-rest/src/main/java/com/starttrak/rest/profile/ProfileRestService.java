package com.starttrak.rest.profile;

import com.starttrak.Ping;
import com.starttrak.jpa.*;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.repo.UserRepo;
import com.starttrak.rest.request.ProfileBean;
import com.starttrak.rest.response.AuthErrorResponse;
import com.starttrak.rest.response.CodeErrorResponse;
import com.starttrak.rest.response.StandardResponse;
import com.starttrak.rest.response.SuccessResponse;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public StandardResponse create(@HeaderParam("x-auth-id") String ownSessionId, ProfileBean request) {
        UserEntity user = userRepo.findByOwnSessionId(ownSessionId).orElseThrow(IllegalStateException::new);
        profileRepo.createSimple(STRK_ID, request.getEmail(),
                request.getFirstName(), request.getLastName(),
                user.getOwnSessionId(), user);
        return new SuccessResponse<>(request);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse update(@HeaderParam("x-auth-id") String ownSessionId, ProfileBean request) {
        ProfileEntity profile = profileRepo.findByOwnSessionId(ownSessionId).orElseThrow(IllegalStateException::new);
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhone(request.getPhone());
//        profile.setPosition(request.getPositionId());
        profile.setCompanyLabel(request.getCompanyLabel());
//        profile.setCountry(request.getCountryId());
//        profile.setRegion(request.getRegionId());
//        profile.setSeniority(request.getSeniorityId());
//        profile.setSizes(request.getSizeId());
        profileRepo.update(profile);
        return new SuccessResponse<>(request); //todo:: improve
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse get(@HeaderParam("x-auth-id") String hdrSessionId,
                                @QueryParam("session_id") String prmSessionId) {
        logger.info("the header found {x-auth-id:" + hdrSessionId + "}");
        logger.info("the parameter found {x-auth-id:" + prmSessionId + "}");
        //so will be found the st profile since only it has public session id
        Optional<UserEntity> user = userRepo.findByOwnSessionId(hdrSessionId);
        if (!user.isPresent()) {
            return new AuthErrorResponse();
        } else {
            Optional<ProfileEntity> profile = profileRepo.findByOwnSessionId(prmSessionId);
            if (!profile.isPresent()) {
                return new CodeErrorResponse(1006, "the profile does not exists");
            } else {
                ProfileEntity dbProfile = profile.get();
                logger.info("the profile = " + dbProfile);
                ProfileBean bnProfile = new ProfileBean(
                        dbProfile.getFirstName(), dbProfile.getLastName(),
                        dbProfile.getEmail(),
                        Optional.ofNullable(dbProfile.getPhone()).orElse(null),
                        Optional.ofNullable(dbProfile.getCountry()).orElse(new CountryEntity()).getId(),
                        Optional.ofNullable(dbProfile.getRegion()).orElse(new RegionEntity()).getId(),
                        dbProfile.getCompanyLabel(),
                        Optional.ofNullable(dbProfile.getPosition()).orElse(new PositionEntity()).getId(),
                        Optional.ofNullable(dbProfile.getIndustry()).orElse(new IndustryEntity()).getId(),
                        Optional.ofNullable(dbProfile.getSeniority()).orElse(new SeniorityEntity()).getId(),
                        Optional.ofNullable(dbProfile.getSizes()).orElse(new SizeEntity()).getId());
                return new SuccessResponse<>(bnProfile);
            }
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfileEntity> search(SearchCondition conditions) {
        profileRepo.findByConditions();
        return new ArrayList<>();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void meet(List<Long> ids) {
        //send emails to the provided user ids
        //and must be sent some kind of confirmation to the sender
        //think about some mail provider (consult DM)
    }

}