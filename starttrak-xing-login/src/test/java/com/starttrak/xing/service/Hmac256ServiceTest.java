package com.starttrak.xing.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.starttrak.xing.BaseTest;


public class Hmac256ServiceTest extends BaseTest{
			
	@Inject
    private Hmac256Service service;
	
	@Test
	@Ignore
	public void testHash(){
		String hash = service.prepareHash("text", "key");
		Assert.assertEquals(hash, "7d4b4e735309cd110f0c6865a57efb01e1ed2a342c251ecd14d3f05c820fd693");
	}
	
}
