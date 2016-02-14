package com.starttrak.rest.profile;

import com.starttrak.Ping;
import com.starttrak.common.SocNetwork;
import com.starttrak.jpa.*;
import com.starttrak.repo.*;
import com.starttrak.rest.auth.AuthenticationException;
import com.starttrak.rest.response.*;
import org.hibernate.HibernateException;
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


    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Ping getPing() {
        logger.info(">>> PING request");
        Ping ping = new Ping();
        ping.setMessage(new Date().toString());
        logger.info("<<< PING request");
        return ping;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse create(@HeaderParam("x-auth-id") String ownSessionId, ProfileBean request) {
        logger.info("x-auth-id:" + ownSessionId + " <- create profile request");
        try {
            UserEntity user = userRepo.findByOwnSessionId(ownSessionId).orElseThrow(AuthenticationException::new);
            profileRepo.createSimple((long) SocNetwork.STTR.getCode(), user.getEmail(),
                    request.getFirstName(), request.getLastName(),
                    user.getOwnSessionId(), user);
            return new SuccessResponse<>(request);
        } catch (AuthenticationException ise) {
            logger.error(ise);
            return new AuthErrorResponse();
        } catch (HibernateException hr) {
            logger.error(hr);
            return new InternalErrorResponse(hr.getMessage());
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse update(@HeaderParam("x-auth-id") String ownSessionId, ProfileBean request) {
        logger.info("x-auth-id:" + ownSessionId + " <- update profile request");
        try {
            userRepo.findByOwnSessionId(ownSessionId).orElseThrow(AuthenticationException::new);
            profileRepo.update(ownSessionId,
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPhone(),
                    request.getPositionId(),
                    request.getCompanyLabel(),
                    request.getCountryId(),
                    request.getRegionId(),
                    request.getCityName(),
                    request.getSeniorityId(),
                    request.getSizeId(),
                    request.getIndustryId()
            );
            return new SuccessResponse<>(request);
        } catch (AuthenticationException ise) {
            logger.error(ise);
            return new AuthErrorResponse();
        } catch (HibernateException hr) {
            logger.error(hr);
            return new InternalErrorResponse(hr.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse get(@HeaderParam("x-auth-id") String hdrSessionId,
                                @QueryParam("session_id") String prmSessionId) {
        logger.info("with " + hdrSessionId + "/" + prmSessionId + " <- get profile request");
        Optional.ofNullable(hdrSessionId).ifPresent(id ->
                logger.info("the header found {x-auth-id:" + id + "}"));
        Optional.ofNullable(prmSessionId).ifPresent(id ->
                logger.info("the parameter found {session-id:" + id + "}"));

        //so will be found the st profile since only it has public session id
        String sessionId = hdrSessionId;
        if (sessionId == null) {
            sessionId = prmSessionId;
        }
        if (sessionId == null) {
            logger.error("cannot identify the session - no header/parameter");
            return new AuthErrorResponse();
        }
        try {
            Optional<UserEntity> user = userRepo.findByOwnSessionId(sessionId);
            if (!user.isPresent()) {
                logger.error("cannot identify an user by x-auth-id:" + sessionId);
                return new AuthErrorResponse();
            } else {
                Optional<ProfileEntity> profile = profileRepo.findByOwnSessionId(sessionId);
                if (!profile.isPresent()) {
                    logger.warn("cannot find any profile for an user by x-auth-id:" + sessionId);
                    return new CodeErrorResponse(1006, "the profile does not exists");
                } else {
                    ProfileEntity dbProfile = profile.get();
                    logger.info("the profile found and will be converted -> " + dbProfile);
                    ProfileBean bnProfile = new ProfileBean(
                            dbProfile.getId(),
                            Optional.ofNullable(dbProfile.getTitle()).orElse(new TitleEntity()).getId(),
                            dbProfile.getFirstName(), dbProfile.getLastName(),
                            dbProfile.getEmail(),
                            Optional.ofNullable(dbProfile.getPhone()).orElse(null),
                            Optional.ofNullable(dbProfile.getCountry()).orElse(new CountryEntity()).getId(),
                            Optional.ofNullable(dbProfile.getRegion()).orElse(new RegionEntity()).getId(),
                            dbProfile.getCityLabel(),
                            dbProfile.getCompanyLabel(),
                            Optional.ofNullable(dbProfile.getPosition()).orElse(new PositionEntity()).getId(),
                            Optional.ofNullable(dbProfile.getIndustry()).orElse(new IndustryEntity()).getId(),
                            Optional.ofNullable(dbProfile.getSeniority()).orElse(new SeniorityEntity()).getId(),
                            Optional.ofNullable(dbProfile.getSizes()).orElse(new SizeEntity()).getId(),
                            dbProfile.getUser().getSourceNetwork().getId(),
                            "http://vignette1.wikia.nocookie.net/logopedia/images/a/a5/Gravity_Guy_icon.png/revision/latest?cb=20140210144551");
                    return new SuccessResponse<>(bnProfile);
                }
            }
        } catch (HibernateException hr) {
            logger.error(hr);
            return new InternalErrorResponse(hr.getMessage());
        }
    }

    @Path("/search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse search(@HeaderParam("x-auth-id") String ownSessionId, SearchRequest conditions) {
        logger.info("x-auth-id:" + ownSessionId + " <- search request");
        try {
            userRepo.findByOwnSessionId(ownSessionId).orElseThrow(AuthenticationException::new);
            List<ProfileEntity> profiles = profileRepo.
                    findAllByNetwork(Page.OPTIONAL_DEFAULT, SocNetwork.STTR);
            return new SuccessResponse<>(profiles.stream().map(dbProfile ->
                            new ProfileBean(
                                    dbProfile.getUser().getId(),
                                    Optional.ofNullable(dbProfile.getTitle()).orElse(new TitleEntity()).getId(),
                                    dbProfile.getFirstName(), dbProfile.getLastName(),
                                    dbProfile.getEmail(),
                                    Optional.ofNullable(dbProfile.getPhone()).orElse(null),
                                    Optional.ofNullable(dbProfile.getCountry()).orElse(new CountryEntity()).getId(),
                                    Optional.ofNullable(dbProfile.getRegion()).orElse(new RegionEntity()).getId(),
                                    dbProfile.getCityLabel(),
                                    dbProfile.getCompanyLabel(),
                                    Optional.ofNullable(dbProfile.getPosition()).orElse(new PositionEntity()).getId(),
                                    Optional.ofNullable(dbProfile.getIndustry()).orElse(new IndustryEntity()).getId(),
                                    Optional.ofNullable(dbProfile.getSeniority()).orElse(new SeniorityEntity()).getId(),
                                    Optional.ofNullable(dbProfile.getSizes()).orElse(new SizeEntity()).getId(),
                                    dbProfile.getUser().getSourceNetwork().getId(),
                                    "http://vignette1.wikia.nocookie.net/logopedia/images/a/a5/Gravity_Guy_icon.png/revision/latest?cb=20140210144551")
            ).collect(Collectors.toList()));
        } catch (AuthenticationException ise) {
            logger.error(ise);
            return new AuthErrorResponse();
        } catch (HibernateException hr) {
            logger.error(hr);
            return new InternalErrorResponse(hr.getMessage());
        }
    }

    @Path("/meet")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse meet(@HeaderParam("x-auth-id") String ownSessionId, MeetRequest meet) {
        logger.info("x-auth-id:" + ownSessionId + " <- meet request");
        try {
            userRepo.findByOwnSessionId(ownSessionId).orElseThrow(AuthenticationException::new);
            meet.getIds().stream().forEach(id -> logger.info("check id{" + id+"}=" +
                    userRepo.find(id).orElse(new UserEntity()).getEmail()));
            return new SuccessResponse<>("success");
        } catch (AuthenticationException ise) {
            logger.error(ise);
            return new AuthErrorResponse();
        } catch (HibernateException hr) {
            logger.error(hr);
            return new InternalErrorResponse(hr.getMessage());
        }
    }

}