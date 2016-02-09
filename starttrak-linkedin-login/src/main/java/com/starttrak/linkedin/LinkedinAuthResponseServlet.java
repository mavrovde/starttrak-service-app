package com.starttrak.linkedin;

import com.starttrak.jpa.NetworkEntity;
import com.starttrak.jpa.ProfileEntity;
import com.starttrak.jpa.UserEntity;
import com.starttrak.repo.NetworkRepo;
import com.starttrak.repo.ProfileRepo;
import com.starttrak.repo.UserRepo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.UUID;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinAuthResponseServlet extends HttpServlet {

    private final static long STRK_ID = 0;
    private final static long LNKD_ID = 1;

    @Inject
    private UserRepo userRepo;

    @Inject
    private ProfileRepo profileRepo;

    @Inject
    private NetworkRepo networkRepo;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter responseWriter = response.getWriter();
        //-=-=-
        if (request.getParameter("code") != null) {
            Client client = Client.create();
            WebResource webResourcePost =
                    client.resource("https://www.linkedin.com/uas/oauth2/accessToken");
            MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
            formData.add("client_id", "774g998g1hvui6");
            formData.add("client_secret", "12WaBJlQxbSndcSI");
            formData.add("grant_type", "authorization_code");
            formData.add("code", request.getParameter("code"));
            formData.add("redirect_uri", "http://mavrov.de:8080/starttrak-linkedin-login/linkedin-auth-response");
            ClientResponse postResponse =
                    webResourcePost.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
            if (postResponse.getStatus() != 200) {
                throw new IllegalStateException("Failed to get access code : " + postResponse.getStatus());
            }
            //-=-=- send the profile request to the linkedin api server
            String accessTokenRes = postResponse.getEntity(String.class);
            String[] accessParts = accessTokenRes.split(",");
            String[] accessTokenParts = accessParts[0].split(":");
            String[] clearAccessToken = accessTokenParts[1].split("\"");
            String appKey = clearAccessToken[1];
            //-=-=-=-
            if (appKey != null) {
                WebResource webResource = client
                        .resource("https://api.linkedin.com/v1/people/~:(email-address,first-name,last-name,headline)?format=json");
                ClientResponse getResponse = webResource.
                        header("Connection", "Keep-Alive").
                        header("Authorization", "Bearer " + appKey).
                        accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
                if (getResponse.getStatus() != 200) {
                    throw new IllegalStateException("Failed to get the profile : " + getResponse.getStatus());
                }
                String textProfile = getResponse.getEntity(String.class);
                //out.println("<h1>PROFILE</h1><br/>");
                //out.println("<h1>" + textProfile + "</h1>");
                // -=-=-=-
                Object jsonParsed = JSONValue.parse(textProfile);
                JSONObject jsonProfile = (JSONObject) jsonParsed;
                responseWriter.println(jsonProfile);
                // -=-=-=-
                //{"firstName":"Sergii","lastName":"Mavrov","emailAddress":"serg.mavrov@gmail.com",
                // "headline":"Senior Java Software Engineer at Amadeus IT Group"}
                //responseWriter.println("<br/>");
                // -=-=-=-
                String firstName = jsonProfile.get("firstName").toString();
                String lastName = jsonProfile.get("lastName").toString();
                String emailAddress = jsonProfile.get("emailAddress").toString();
                String[] positionCompany = jsonProfile.get("headline").toString().split(" at ");
                String position = positionCompany[0];
                String company = positionCompany[1];
                // -=-=-=-
                //responseWriter.println(firstName + "<br/>");
                //responseWriter.println(lastName + "<br/>");
                //responseWriter.println(emailAddress + "<br/>");
                //responseWriter.println(position + "<br/>");
                //responseWriter.println(company + "<br/>");
                //responseWriter.println("<br/>");
                // -=-=-=-
                responseWriter.println("OwnSessionID = " +
                        updateLinkedinProfile(emailAddress, firstName, lastName,
                                position, company, appKey) + "<br/>"
                );
            }
            // -=-=-
        }

    }

    private String updateLinkedinProfile(String email, String firstName, String lastName,
                                         String position, String company, String appKey) {
        Optional<ProfileEntity> linkedinProfile = profileRepo.findByEmailNetwork(LNKD_ID, email);
        UserEntity user; // we are trying to define current user
        if (!linkedinProfile.isPresent()) { //there is no linkedin profile
            Optional<ProfileEntity> otherProfile = profileRepo.findByEmail(email);
            if (!otherProfile.isPresent()) { //there is no any social profiles
                // create an user for starttrak
                user = userRepo.create(email);
                // create the starttrak profile
                profileRepo.create(STRK_ID, email, firstName, lastName, position, company,
                        user.getOwnSessionId(), user);
            } else {
                // there was found at least one social profile, so take an user
                user = otherProfile.get().getUser();
            }
            // create linkedin profile
            profileRepo.create(LNKD_ID, email, firstName, lastName, position, company, appKey, user);
            // -=-=-=-
        } else { // we have already linkedin profile
            linkedinProfile.get().setNetworkToken(appKey);
            profileRepo.update(linkedinProfile.get());
            user = linkedinProfile.get().getUser();
        }
        return user.getOwnSessionId();
    }

}
