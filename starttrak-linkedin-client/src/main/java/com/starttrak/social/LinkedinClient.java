package com.starttrak.social;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.jboss.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
@Linkedin
@RequestScoped
public class LinkedinClient implements SocialNetworkClient {

    private final static Logger logger = Logger.getLogger(LinkedinClient.class);

    @Override
    public SocialNetworkProfile getProfileByAccessToken(String accessToken) throws SocialNetworkException {
        String textProfile = getJsonProfileByAccessToken(accessToken);
        Object parsedProfile = JSONValue.parse(textProfile);
        JSONObject jsonProfile = (JSONObject) parsedProfile;
        // -=-=-=-
        String firstName = jsonProfile.get("firstName").toString();
        String lastName = jsonProfile.get("lastName").toString();
        String emailAddress = jsonProfile.get("emailAddress").toString();

        String[] positionCompany = jsonProfile.get("headline").toString().split(" at ");
        String position;
        String company = null;
        if (positionCompany.length > 1) {
            position = positionCompany[0];
            company = positionCompany[1];
        } else {
            position = positionCompany[0];
        }

        String pictures = jsonProfile.get("pictureUrls").toString();
        Object parsedPictures = JSONValue.parse(pictures);
        JSONObject jsonPictures = (JSONObject) parsedPictures;
        JSONArray pictureUrls = (JSONArray) jsonPictures.get("values");
        String pictureUrl = null;
        if (pictureUrls != null && !pictureUrls.isEmpty()) {
            pictureUrl = pictureUrls.stream().findAny().toString();
        }

        //-=-=-=- location (geo)
        String location = jsonProfile.get("location").toString();
        JSONObject jsonLocation = (JSONObject) JSONValue.parse(location);
        logger.info("json location = " + jsonLocation);
        String fullName = jsonLocation.get("name").toString();
        String[] fullNames = fullName.split(",");
        String cityName = null;
        String countryLabel = null;
        if (fullNames.length > 1) {
            cityName = fullNames[0].trim();
            countryLabel = fullNames[1].trim();
        }
        //-=-=-=-

        return LinkedinProfile.createNew(firstName, lastName, emailAddress,
                Optional.ofNullable(position),
                Optional.ofNullable(null)/*industry*/,
                Optional.ofNullable(null)/*seniority*/,
                Optional.ofNullable(company),
                Optional.ofNullable(null)/*sizes*/,
                Optional.ofNullable(pictureUrl),
                Optional.ofNullable(cityName)/*city*/,
                Optional.ofNullable(null)/*region*/,
                Optional.ofNullable(countryLabel)/*country*/);
    }

    @Override
    public String getJsonProfileByAccessToken(String accessToken) throws SocialNetworkException {
        Client client = Client.create();
        WebResource webResource = client
                .resource("https://api.linkedin.com/v1/people/~:" +
                        "(email-address,first-name,last-name," +
                        "headline,location,positions,industry," +
                        "picture-urls::(original))" +
                        "?format=json");
        ClientResponse getResponse = webResource.
                header("Connection", "Keep-Alive").
                header("Authorization", "Bearer " + accessToken).
                accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (getResponse.getStatus() != 200) {
            throw new SocialNetworkException("Failed to get the profile : " + getResponse.getStatus());
        }
        return getResponse.getEntity(String.class);
    }

    @Override
    public String getAccessToken(String code) {
        Client client = Client.create();
        WebResource webResourcePost =
                client.resource("https://www.linkedin.com/uas/oauth2/accessToken");
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("client_id", "774g998g1hvui6");
        formData.add("client_secret", "12WaBJlQxbSndcSI");
        formData.add("grant_type", "authorization_code");
        formData.add("code", code);
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
        return clearAccessToken[1];
    }

}
