package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.DashboardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.SubCategoryPage;

public class SubCategoryTest extends Base{
	LoginPage loginpage;
	DashboardPage dashboardpage;
	SubCategoryPage subcategorypage;
	
	@Test
	public void verifySubCategoryPageHeader()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		subcategorypage=new SubCategoryPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Sub Category");
		String actualHeader=subcategorypage.getHeader();
		System.out.println(actualHeader);
		String expectedHeader="List Sub Categories";
		Assert.assertEquals(actualHeader, expectedHeader);
	}
	
	@Test
	public void verifySuccessfulNewCategoryAddition()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		subcategorypage=new SubCategoryPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Sub Category");
		subcategorypage.clickOnNewButton();
		subcategorypage.selectCategory("Vegetables");
		subcategorypage.enterSubCategory();
		subcategorypage.chooseFileImg();
		//subcategorypage.clickOnSaveButton();
	}
	
	@Test
	public void verifyUserIsAbleToSearchSubCategories()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		subcategorypage=new SubCategoryPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Sub Category");
		subcategorypage.clickOnSearchButton();
		subcategorypage.selectSearchCategory("Vegetables");
		subcategorypage.enterSearchSubCatField();
		subcategorypage.clickOnSubCatSearchButton();
		boolean actual=subcategorypage.CheckSearchItemPresent("Onion");
		Assert.assertTrue(actual);
		
		}
}
