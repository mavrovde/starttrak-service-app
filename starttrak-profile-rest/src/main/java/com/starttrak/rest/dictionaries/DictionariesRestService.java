package com.starttrak.rest.dictionaries;

import com.starttrak.jpa.StandardEntity;
import com.starttrak.jpa.TitleEntity;
import com.starttrak.repo.*;
import com.starttrak.rest.response.AuthErrorResponse;
import com.starttrak.rest.response.StandardResponse;
import com.starttrak.rest.response.SuccessResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author serg.mavrov@gmail.com
 */
@Path("/dictionaries")
@ApplicationScoped
public class DictionariesRestService {

    @Inject
    private UserRepo userRepo;

    @Inject
    private CountryRepo countriesRepo;

    @Inject
    private RegionRepo regionsRepo;

    @Inject
    private PositionRepo positionsRepo;

    @Inject
    private SizeRepo sizesRepo;

    @Inject
    private IndustryRepo industriesRepo;

    @Inject
    private SeniorityRepo seniorityRepo;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse getAllDictionaries(@HeaderParam("x-auth-id") String ownSessionId) {
        try {
            Map<String, List<? extends StandardEntity>> content = new HashMap<>();
            userRepo.findByOwnSessionId(ownSessionId).orElseThrow(IllegalStateException::new);
            // -=-=-=-
            content.put("countries", countriesRepo.findAllBy(Page.OPTIONAL_DEFAULT));
            content.put("states", regionsRepo.findAllBy(Page.OPTIONAL_DEFAULT));
            content.put("positions", positionsRepo.findAllBy(Page.OPTIONAL_DEFAULT));
            content.put("sizes", sizesRepo.findAllBy(Page.OPTIONAL_DEFAULT));
            content.put("industries", industriesRepo.findAllBy(Page.OPTIONAL_DEFAULT));
            content.put("seniority", seniorityRepo.findAllBy(Page.OPTIONAL_DEFAULT));
            content.put("titles", new ArrayList<>(
                    Arrays.asList(new TitleEntity(1L, "Mr."),
                            new TitleEntity(2L, "Ms."),
                            new TitleEntity(3L, "Mrs."))
            ));
            // -=-=-=-
            return new SuccessResponse<>(content);
        } catch (IllegalStateException ise) {
            return new AuthErrorResponse();
        }
    }

}
