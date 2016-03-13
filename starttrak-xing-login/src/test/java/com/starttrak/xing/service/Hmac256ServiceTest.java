package com.starttrak.xing.service;

import javax.inject.Inject;

import org.junit.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.starttrak.xing.BaseTest;


@RunWith(Arquillian.class)
public class Hmac256ServiceTest extends BaseTest{
			
	@Inject
    private Hmac256Service service;
	
	@Test
	public void testHash(){
		String hash = service.prepareHash("text", "key");
		Assert.assertEquals(hash, "7d4b4e735309cd110f0c6865a57efb01e1ed2a342c251ecd14d3f05c820fd693");
	}
	
}
