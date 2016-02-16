package com.starttrak.social;

import java.util.Optional;

public class FacebookProfile extends DefaultProfile {

    public static SocialNetworkProfile createNew(String firstName, String lastName,
                                                 String emailAddress,
                                                 Optional<String> position, Optional<String> industry,
                                                 Optional<String> seniority, Optional<String> company,
                                                 Optional<String> sizes, Optional<String> pictureUrl,
                                                 Optional<String> cityName, Optional<String> region,
                                                 Optional<String> country) {
        return new FacebookProfile(firstName, lastName,
                emailAddress,
                position, industry,
                seniority, company,
                sizes, pictureUrl,
                cityName, region, country);
    }

    private FacebookProfile(String firstName, String lastName, String emailAddress,
                            Optional<String> position, Optional<String> industry, Optional<String> seniority,
                            Optional<String> company, Optional<String> sizes,
                            Optional<String> pictureUrl, Optional<String> cityName,
                            Optional<String> region, Optional<String> country) {
        super(firstName, lastName, emailAddress, position, industry, seniority, company, sizes,
                pictureUrl, cityName, region, country);
    }

}
