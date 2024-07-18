package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.DashboardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.SubCategoryPage;

public class SubCategoryTest extends Base{
	LoginPage loginpage;
	DashboardPage dashboardpage;
	SubCategoryPage subcategorypage;
	SoftAssert softassert=new SoftAssert();
	
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
		subcategorypage.createNewSubCategory("Vegetables", "Capsicum");
		subcategorypage.chooseFileImg();
		subcategorypage.clickOnSaveButton();
		String actualAlert=subcategorypage.getSuccessMsgNewSubCategory();
		System.out.println(actualAlert);
		String expectedAlert="Sub Category Created Successfully";
		softassert.assertTrue(actualAlert.contains(expectedAlert));
		subcategorypage.deleteSubCategory();
		
	}
	
	@Test
	public void verifyUserIsAbleToSearchSubCategories()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		subcategorypage=new SubCategoryPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Sub Category");
		subcategorypage.checkSearchButton("Vegetables", "onion");
		boolean actual=subcategorypage.checkSearchItemPresent("Onion");
		Assert.assertTrue(actual);
		
		}
	@Test
	public void verifyUnsuccessfulDuplicateSubCategoryCreation()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		subcategorypage=new SubCategoryPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Sub Category");
		subcategorypage.createDuplicateSubCategory("Vegetables", "onion");
		boolean actual=subcategorypage.checkAlreadyExistAlertMsg("Sub Category already exists.");
		Assert.assertTrue(actual);
	}
	
	@Test
	public void verifyAdminIsAbleToDeleteSubCategory()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		subcategorypage=new SubCategoryPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Sub Category");
		subcategorypage.createNewSubCategory("Vegetables", "Radish");
		subcategorypage.clickOnSaveButton();
		subcategorypage.deleteSubCategory();
		boolean actualAlert=subcategorypage.checkDeleteSuccessAlertMsg("Sub Category Deleted Successfully");
		Assert.assertTrue(actualAlert);
	}
}
