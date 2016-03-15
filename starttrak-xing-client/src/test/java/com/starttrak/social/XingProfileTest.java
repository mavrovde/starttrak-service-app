package com.starttrak.social;

import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.*;

public class XingProfileTest {
	
	@Test
	public void equals(){
		XingProfile p1 = new XingProfile();
		XingProfile p2 = new XingProfile();
		assertTrue(p1.equals(p2));
		
		p1.setIndustry(Optional.ofNullable(null));
		assertFalse(p1.equals(p2));
		
		p2.setIndustry(Optional.ofNullable(null));
		assertTrue(p1.equals(p2));
	}
	
	@Test
	public void _toString(){
		XingProfile p1 = new XingProfile();
		p1.setFirstName("Alexander");
		p1.setCityName(Optional.of("Moscow"));
		p1.setRegion(Optional.ofNullable(null));
		String str = p1.toString();
		assertTrue(str.contains("[firstName=Alexander,lastName=<null>,emailAddress=<null>,position=<null>,industry=<null>,seniority=<null>,company=<null>,sizes=<null>,pictureUrl=<null>,cityName=Optional[Moscow],region=Optional.empty,country=<null>]"));
	}
	
	@Test
	public void _hashCode(){
		XingProfile p1 = new XingProfile();
		assertEquals(p1.hashCode(),1110457057);
		
		p1.setCityName(Optional.ofNullable("Moscow"));
		assertEquals(p1.hashCode(),-1233153077);
	}
	
	
	
	
	
}
