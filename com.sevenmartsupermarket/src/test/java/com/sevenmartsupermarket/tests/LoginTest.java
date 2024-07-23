package com.sevenmartsupermarket.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.constants.Constants;
import com.sevenmartsupermarket.pages.DashboardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.ExcelReader;

import DataProviders.DataProviderEx;


public class LoginTest extends Base {
	LoginPage loginpage;
	DashboardPage dashboardpage;
	ExcelReader excelreader=new ExcelReader();
	@Test (dataProvider="credentials", dataProviderClass = DataProviderEx.class)
	public void verifyLoginWithValidCredentials(String userName, String password, String profileName)
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login(userName,password);
		String actualProfileName=dashboardpage.getProfileName();
		Assert.assertEquals(actualProfileName,profileName);
	}
	@Test(groups = "regression")
	public void verifyInvalidLoginAlertMsg()
	{
		loginpage= new LoginPage(driver);
		loginpage.login("123","admin");
		String actualAlertMsg=loginpage.getAlertMessage();
		Assert.assertTrue(actualAlertMsg.contains(Constants.INVALID_LOGIN_ALERT));
	}
	@Test
	public void verifyRememberMeCheckBox()
	{
		loginpage= new LoginPage(driver);
		loginpage.clickRememberMeCheckBox();
		Assert.assertTrue(loginpage.CheckIsRememberMeSelected());
		
		
	}

}
