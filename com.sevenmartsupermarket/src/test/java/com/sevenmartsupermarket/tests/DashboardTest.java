package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.constants.Constants;
import com.sevenmartsupermarket.listeners.RetryAnalyzer;
import com.sevenmartsupermarket.pages.DashboardPage;
import com.sevenmartsupermarket.pages.LoginPage;

public class DashboardTest extends Base{
	LoginPage loginpage;
	DashboardPage dashboardpage;
	
	@Test
	public void verifyWebsiteName()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		String actualHeader=dashboardpage.getWebSiteHeader();
		Assert.assertEquals(actualHeader, Constants.WEBSITE_HEADER);
	}
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifyAdminIsAbleToNavigateToCards()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		dashboardpage.navigateToCard("Manage Pages");
		boolean actualResult=dashboardpage.checkForNewButton();
		Assert.assertTrue(actualResult);
		
	}
	@Test
	public void verifyUserIsAbleToLogOutSuccessfully()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		dashboardpage.clickOnLogout();
		boolean actualSignInMsg=dashboardpage.checkSignInMsg(Constants.SIGN_IN_MSG);
		Assert.assertTrue(actualSignInMsg);
	}

}
