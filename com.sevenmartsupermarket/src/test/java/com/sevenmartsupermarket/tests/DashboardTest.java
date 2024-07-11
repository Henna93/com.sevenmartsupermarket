package com.sevenmartsupermarket.tests;

import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.listeners.RetryAnalyzer;
import com.sevenmartsupermarket.pages.DashboardPage;
import com.sevenmartsupermarket.pages.LoginPage;

public class DashboardTest extends Base{
	LoginPage loginpage;
	DashboardPage dashboardpage;
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifyManagePagesCardIsClickable()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		
	}

}
