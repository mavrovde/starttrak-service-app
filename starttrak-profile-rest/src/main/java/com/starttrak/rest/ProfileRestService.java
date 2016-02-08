package com.starttrak.rest;

import com.starttrak.Ping;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.repo.StandardRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author serg.mavrov@gmail.com
 */
@Path("/profile")
@ApplicationScoped
public class ProfileRestService {

	@Inject
	private StandardRepository<ProfileEntity> profileRepo;

	@GET
	@Path("/ping")
	@Produces(MediaType.APPLICATION_JSON)
	public Ping getPing() {
		Ping ping = new Ping();
		ping.setMessage(new Date().toString());
		return ping;
	}

//	@POST
//	@Path("/post")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createTrackInJSON(Ping ping) {
//		String result = "Ping saved : " + ping;
//		return Response.status(201).entity(result).build();
//	}
	
}