package com.starttrak.social;

import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.*;

public class JustTest {

	@Test
	public void testOptional(){
		Optional<String> v = Optional.of("333");
		assertTrue(v.isPresent());
		
		v = Optional.ofNullable("444");
		assertTrue(v.isPresent());
		v.ifPresent(JustTest::print);
		System.out.println(v.orElse("666"));
		
		v = Optional.ofNullable(null);
		assertFalse(v.isPresent());
		System.out.println(v.orElse("777"));
		
		
		v = Optional.empty();
		assertFalse(v.isPresent());
		
		
		
	}
	
	public static void print(String s){
		System.out.println("========" + s + "==========");
	}
	
}
