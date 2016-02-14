package com.starttrak.social;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author serg.mavrov@gmail.com
 */
@RunWith(Arquillian.class)
public class LinkedClientTest {

    private final static Logger logger = Logger.getLogger(LinkedClientTest.class);

    private final static String ACCESS_TOKEN =
            "AQVz3r_gUlFS_vGFXDXbUkYbCwhuDxPwPT2e9S3dYTtde8mDqQAaP1a8IWQ" +
                    "ekdWfRYGHll7__yklyo033NEweAQd6hZGIfHxICC2bB-bgmAC7k" +
                    "tmzYTbd_qo9adhfg6Ug3W59pX3GD2fhjrQcJPYdev8qYlYIjluD" +
                    "Qaa1c9ugGm-hA4Al6A";

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
        SocialNetworkProfile profile = linkedinClient.getProfileByAccessToken(ACCESS_TOKEN);
        logger.info("the linkedin profile is " + profile);
        assertEquals("Sergii", profile.getFirstName());
        assertEquals("Mavrov", profile.getLastName());
        assertEquals("https://media.licdn.com/mpr/mprx/0_196YPWKkQvyAm37c" +
                "AFRMkAlGLRyqmT3BikRZGcDkQUAN0SuqNPRpFArF9L8cmSTliPRR1alFL" +
                "uKAuKpc3gBAktAktuKluKgBtgByAWDXFn3ZuF2OtJbgR0mI1W", profile.getPictureUrl());
    }

    @Test
    public void testJsonProfile() throws SocialNetworkException{
        assertNotNull(linkedinClient);
        String jsonProfile = linkedinClient.getJsonProfileByAccessToken(ACCESS_TOKEN);
        logger.info("the json profile is " + jsonProfile);
    }
}
