package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
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
		dashboardpage= new DashboardPage(driver);
		loginpage.login();
		String actualHeader=dashboardpage.getWebSiteHeader();
		System.out.println(actualHeader);
		String expectedHeader="7rmart supermarket";
		Assert.assertEquals(actualHeader, expectedHeader);
	}
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifyAdminIsAbleToNavigateToCards()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Manage Pages");
		boolean actual=dashboardpage.checkForNewButton("New");
		Assert.assertTrue(actual);
		
	}
	@Test
	public void verifyUserIsAbleToLogOutSuccessfully()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		loginpage.login();
		dashboardpage.clickOnLogout();
		boolean actualMsg=dashboardpage.checkSignInMsg("Sign in to start your session");
		Assert.assertTrue(actualMsg);
	}

}
