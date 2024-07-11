package com.sevenmartsupermarket.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.DashboardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.ExcelReader;

import DataProviders.DataProviderEx;


public class LoginTest extends Base {
	LoginPage loginpage;
	DashboardPage dashboardpage;
	ExcelReader excelreader=new ExcelReader();
	@Test (dataProvider="credentials", dataProviderClass = DataProviderEx.class)
	public void verifyLogin(String userName, String password, String profileName)
	{
		loginpage= new LoginPage(driver);
		dashboardpage=new DashboardPage(driver);
		
		//loginpage.login();
		loginpage.login(userName,password);
		
		String actualProfileName=dashboardpage.getProfileName();
		System.out.println(actualProfileName);
		
		Assert.assertEquals(actualProfileName,profileName);
	}
	@Test(groups = "regression")
	public void verifyInvalidLoginAlertMsg()
	{
		loginpage= new LoginPage(driver);
		loginpage.login("123","admin");
		String actualAlertMsg=loginpage.getAlertMessage();
		System.out.println(actualAlertMsg);
		String expectedAlertMsg="Alert!";
		Assert.assertTrue(actualAlertMsg.contains(expectedAlertMsg));
	}
	

}
