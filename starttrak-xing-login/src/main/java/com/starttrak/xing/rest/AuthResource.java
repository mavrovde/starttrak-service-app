package com.starttrak.xing.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;



/**
 * 
 * @author Alexander Babin
 * @version 1.0
 * @since 12.03.2016
 *
 */

@Path("/auth")
public class AuthResource extends BaseResource{
			
	@POST
	@Produces("application/json")
	public Response auth(@Context HttpServletRequest request){
		return Response.status(200).entity("{}").build();
	}
	
	/*
	private String readBodyAsString(HttpServletRequest request){
		try(InputStream is = request.getInputStream()){
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        char[] charBuffer = new char[1024];
	        int bytesRead = -1;
	        while ((bytesRead = br.read(charBuffer)) > 0) {
	            sb.append(charBuffer, 0, bytesRead);
	        }
	        return sb.toString();
		} catch (IOException e) {
			log.error("Error during read content from body! " , e);
		} 
        return null;
	}
	*/
	
}
