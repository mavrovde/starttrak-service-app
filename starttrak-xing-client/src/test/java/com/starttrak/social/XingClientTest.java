package com.starttrak.social;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;


public class XingClientTest extends BaseTest{
	
	@Inject @Xing private SocialNetworkClient client;
	
	@Test
	public void test() throws SocialNetworkException{
		String accessToken = "6d176908c2b9c1f402ba" + "&" + "4d458965f9d42c2145be";
		SocialNetworkProfile profile = client.getProfileByAccessToken(accessToken);
		log.info("Obtain profile: " + profile);
		Assert.assertNotNull(profile);
	}
	
}
