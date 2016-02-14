package com.starttrak.social;

import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
public interface SocialNetworkProfile {

    String getFirstName();

    String getLastName();

    String getEmailAddress();

    Optional<String> getPosition();

    Optional<String> getIndustry();

    Optional<String> getSizes();

    Optional<String> getCompany();

    Optional<String> getPictureUrl();

    Optional<String> getCountry();

    Optional<String> getRegion();

    Optional<String> getCityName();

}
