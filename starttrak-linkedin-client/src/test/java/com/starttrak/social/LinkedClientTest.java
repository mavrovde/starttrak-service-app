package com.starttrak.social;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * @author serg.mavrov@gmail.com
 */
@RunWith(Arquillian.class)
public class LinkedClientTest {

    private final static Logger logger = Logger.getLogger(LinkedClientTest.class);

    private final static String ACCESS_TOKEN = "12345";

    @Deployment
    public static JavaArchive getJar() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "com.starttrak.social")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    @Linkedin
    private SocialNetworkClient linkedinClient;

    @Test
    public void testGetProfile(){
        Assert.assertNotNull(linkedinClient);
    }

}
