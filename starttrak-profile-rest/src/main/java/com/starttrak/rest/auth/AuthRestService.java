package com.starttrak.rest.auth;

import com.starttrak.jpa.UserEntity;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.repo.UserRepo;
import com.starttrak.rest.request.RegRequest;
import com.starttrak.rest.response.StandardResponse;
import com.starttrak.rest.response.SuccessResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@Path("/auth")
@ApplicationScoped
public class AuthRestService {

    private final static long STRK_ID = 0;

    @Inject
    private UserRepo userRepo;

    @Inject
    private ProfileRepo profileRepo;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse login(RegRequest regRequest) {
        switch (regRequest.getSocNetworkId()) {
            case 0:
                Optional<UserEntity> user = userRepo.findByEmail(regRequest.getEmail());
                if (user.isPresent()) {
                    return new SuccessResponse<>(new OwnSession(user.get().getOwnSessionId()));
                } else {
                    return new SuccessResponse<>(new OwnSession(userRepo.create(
                            regRequest.getEmail(),
                            regRequest.getPassword(),
                            "registered by starttrak"
                    ).getOwnSessionId()));
                }
            case 1:
                throw new IllegalStateException("no network login implemented");
            case 2:
                throw new IllegalStateException("no network login implemented");
            case 3:
                throw new IllegalStateException("no network login implemented");
            default:
                throw new IllegalStateException("no network login implemented");
        }
    }

}
