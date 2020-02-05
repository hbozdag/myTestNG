package com.class04;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ListenersDemo {

	@Test(groups="regression")
	public void test1() {
		System.out.println("I am test 1");
		Assert.assertTrue(false);
	}
	
	@Test(groups="regression")
	public void test2() {
		System.out.println("I am test 2");
	}
	
	@Test(groups="regression")
	public void test3() {
		System.out.println("I am test 3");
		Assert.assertTrue(false);
	}
}