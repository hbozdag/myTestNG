package com.class01;

import org.testng.annotations.*;

public class TaskAnnotations {
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("This is before class annotation");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("This is after class annotation");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This is before method annotation");
	}
	
	@AfterMethod
	
	public void afterMethod() {
		System.out.println("This is after method annotation");
	}
	
	@Test
	public void testA1() {
		System.out.println("Test me first ");
	}
	@Test
	public void testZ9() {
		System.out.println("Test me last");
	}
}
