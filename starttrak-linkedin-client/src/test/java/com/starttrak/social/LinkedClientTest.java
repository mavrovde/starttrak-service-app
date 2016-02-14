package com.starttrak.social;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * @author serg.mavrov@gmail.com
 */
@RunWith(Arquillian.class)
public class LinkedClientTest {

    private final static Logger logger = Logger.getLogger(LinkedClientTest.class);

    private final static String SERGII_ACCESS_TOKEN =
            "AQVz3r_gUlFS_vGFXDXbUkYbCwhuDxPwPT2e9S3dYTtde8mDqQAaP1a8IWQ" +
                    "ekdWfRYGHll7__yklyo033NEweAQd6hZGIfHxICC2bB-bgmAC7k" +
                    "tmzYTbd_qo9adhfg6Ug3W59pX3GD2fhjrQcJPYdev8qYlYIjluD" +
                    "Qaa1c9ugGm-hA4Al6A";

    private final static String DENIS_ACCESS_TOKEN =
            "AQUMXQTBS6mxAH9I4-_HUArMQ_bsAKDRRYJ_R2criIDWgV_VfJhaHxQebZEO" +
                    "tISWmtvzbaGJxI7rVtWmEpwjM2xPPFqEuHYgZSNnbLmvkMmkCi4w" +
                    "btHlyGujdaCcvwqFpiSohRvTSh0ZAz_qEeK89QbzaL2-9g9oLZLIC" +
                    "tnynySsYLkW4M4";

    @Deployment
    public static JavaArchive getJar() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "com.starttrak.social")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    @Linkedin
    private SocialNetworkClient linkedinClient;

    @Test
    public void testGetObjectProfile() throws SocialNetworkException {
        assertNotNull(linkedinClient);
        SocialNetworkProfile profile = linkedinClient.getProfileByAccessToken(SERGII_ACCESS_TOKEN);
        logger.info("the linkedin profile is " + profile);
        assertEquals("Sergii", profile.getFirstName());
        assertEquals("Mavrov", profile.getLastName());
        assertTrue(profile.getPictureUrl().isPresent());
        assertEquals("https://media.licdn.com/mpr/mprx/0_196YPWKkQvyAm37c" +
                "AFRMkAlGLRyqmT3BikRZGcDkQUAN0SuqNPRpFArF9L8cmSTliPRR1alFL" +
                "uKAuKpc3gBAktAktuKluKgBtgByAWDXFn3ZuF2OtJbgR0mI1W", profile.getPictureUrl().get());
        assertTrue(profile.getCityName().isPresent());
        assertFalse(profile.getRegion().isPresent());
        assertTrue(profile.getCountry().isPresent());

    }

    @Test
    public void testJsonProfile() throws SocialNetworkException{
        assertNotNull(linkedinClient);
        String sergiiProfile = linkedinClient.getJsonProfileByAccessToken(SERGII_ACCESS_TOKEN);
        logger.info("the json profile is " + sergiiProfile);
        String denisProfile = linkedinClient.getJsonProfileByAccessToken(DENIS_ACCESS_TOKEN);
        logger.info("the json profile is " + denisProfile);
    }
}
