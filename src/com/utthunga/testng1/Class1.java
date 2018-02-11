package com.utthunga.testng1;

import org.testng.annotations.Test;

public class Class1 {
	
	@Test(priority=1)
	
	public void TC_001()
	{
		System.out.println("Test_01_ANT");
	}

	@Test(priority=1,enabled=false)
	public void TC_002()
	{
		System.out.println("Test_02_ANT");
	}
	@Test(priority=2,description="testcase_03")
	public void TC_003()
	{
		System.out.println("Test_03_ANT");
	}
	@Test(priority=4)
	public void TC_004()
	{
		System.out.println("Test_04_ANT");
	}
	@Test(priority=5)
	public void TC_005()
	{
		System.out.println("Test_05");
	}
	
	
	
}
