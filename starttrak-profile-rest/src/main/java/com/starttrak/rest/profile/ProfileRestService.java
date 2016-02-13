package com.starttrak.rest.profile;

import com.starttrak.Ping;
import com.starttrak.common.SocNetwork;
import com.starttrak.jpa.*;
import com.starttrak.repo.*;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author serg.mavrov@gmail.com
 */
@Path("/profile")
@ApplicationScoped
public class ProfileRestService {

    private final static Logger logger = Logger.getLogger(ProfileRestService.class);

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    private UserRepo userRepo;

    @Inject
    private CountryRepo countryRepo;
    @Inject
    private RegionRepo regionRepo;
    @Inject
    private SizeRepo sizeRepo;
    @Inject
    private SeniorityRepo seniorityRepo;
    @Inject
    private PositionRepo positionRepo;
    @Inject
    private IndustryRepo industryRepo;


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
        profileRepo.createSimple((long) SocNetwork.STTR.getCode(), user.getEmail(),
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
        profile.setPosition(positionRepo.find(request.getPositionId()).orElseThrow(IllegalArgumentException::new));
        profile.setCompanyLabel(request.getCompanyLabel());
        profile.setCountry(countryRepo.find(request.getCountryId()).orElseThrow(IllegalArgumentException::new));
        profile.setRegion(regionRepo.find(request.getRegionId()).orElseThrow(IllegalArgumentException::new));
        profile.setSeniority(seniorityRepo.find(request.getSeniorityId()).orElseThrow(IllegalArgumentException::new));
        profile.setSizes(sizeRepo.find(request.getSizeId()).orElseThrow(IllegalArgumentException::new));
        profile.setIndustry(industryRepo.find(request.getIndustryId()).orElseThrow(IllegalArgumentException::new));
        profileRepo.update(profile);
        return new SuccessResponse<>(request);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse get(@HeaderParam("x-auth-id") String hdrSessionId,
                                @QueryParam("session_id") String prmSessionId) {
        logger.info("the header found {x-auth-id:" + hdrSessionId + "}");
        logger.info("the parameter found {x-auth-id:" + prmSessionId + "}");
        //so will be found the st profile since only it has public session id
        String sessionId = hdrSessionId;
        if (sessionId == null) {
            sessionId = prmSessionId;
        }
        if (sessionId == null) {
            return new AuthErrorResponse();
        }
        Optional<UserEntity> user = userRepo.findByOwnSessionId(sessionId);
        if (!user.isPresent()) {
            return new AuthErrorResponse();
        } else {
            Optional<ProfileEntity> profile = profileRepo.findByOwnSessionId(sessionId);
            if (!profile.isPresent()) {
                return new CodeErrorResponse(1006, "the profile does not exists");
            } else {
                ProfileEntity dbProfile = profile.get();
                logger.info("the profile = " + dbProfile);
                ProfileBean bnProfile = new ProfileBean(
                        dbProfile.getId(),
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
    public StandardResponse search(SearchRequest conditions) {
        List<ProfileEntity> profiles = profileRepo.findAllBy(Page.OPTIONAL_DEFAULT);
        return new SuccessResponse<>(profiles.stream().map(dbProfile ->
                        new ProfileBean(
                                dbProfile.getId(),
                                dbProfile.getFirstName(), dbProfile.getLastName(),
                                dbProfile.getEmail(),
                                Optional.ofNullable(dbProfile.getPhone()).orElse(null),
                                Optional.ofNullable(dbProfile.getCountry()).orElse(new CountryEntity()).getId(),
                                Optional.ofNullable(dbProfile.getRegion()).orElse(new RegionEntity()).getId(),
                                dbProfile.getCompanyLabel(),
                                Optional.ofNullable(dbProfile.getPosition()).orElse(new PositionEntity()).getId(),
                                Optional.ofNullable(dbProfile.getIndustry()).orElse(new IndustryEntity()).getId(),
                                Optional.ofNullable(dbProfile.getSeniority()).orElse(new SeniorityEntity()).getId(),
                                Optional.ofNullable(dbProfile.getSizes()).orElse(new SizeEntity()).getId())
        ).collect(Collectors.toList()));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse meet(MeetRequest meet) {
        return new SuccessResponse<>("success");
    }

}