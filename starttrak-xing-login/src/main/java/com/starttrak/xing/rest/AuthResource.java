package com.starttrak.xing.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



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
		JSONObject jsonBody = obtainBodyAsJson(request);
		
		return Response.status(200).entity("{}").build();
	}
	
	private JSONObject obtainBodyAsJson(HttpServletRequest request){
		String bodyStr = readBodyAsString(request);
		if(bodyStr != null){
			try {
		        JSONParser parser = new JSONParser();
		        return (JSONObject) parser.parse(bodyStr); 
			} catch (ParseException e) {
				log.error("Error during parse string to JSON! String: "  + bodyStr, e);
			}
		}
		return null;
	}
	
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
	
}
