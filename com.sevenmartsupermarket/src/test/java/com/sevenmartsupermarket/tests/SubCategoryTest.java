package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.constants.Constants;
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
		dashboardpage=loginpage.login();
		subcategorypage=dashboardpage.navigateToSubCategoryCard();
		String actualHeader=subcategorypage.getHeader();
		Assert.assertEquals(actualHeader, Constants.SUBCATEGORY_HEADER);
	}
	
	@Test
	public void verifySuccessfulNewCategoryAddition()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		subcategorypage=dashboardpage.navigateToSubCategoryCard();
		subcategorypage.createNewSubCategory("Vegetables", "Capsicum");
		subcategorypage.chooseFileImg();
		subcategorypage.clickOnSaveButton();
		String actualSuccessAlert=subcategorypage.getSuccessMsgNewSubCategory();
		softassert.assertTrue(actualSuccessAlert.contains(Constants.SUBCAT_CREATION_SUCCESS_MSG));
		subcategorypage.deleteSubCategory();
		
	}
	
	@Test
	public void verifyUserIsAbleToSearchSubCategories()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		subcategorypage=dashboardpage.navigateToSubCategoryCard();
		subcategorypage.checkSearchButton("Vegetables", "onion");
		boolean actualSearchResult=subcategorypage.checkSearchItemPresent("Onion");
		Assert.assertTrue(actualSearchResult);
		
		}
	@Test
	public void verifyUnsuccessfulDuplicateSubCategoryCreation()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		subcategorypage=dashboardpage.navigateToSubCategoryCard();
		subcategorypage.createDuplicateSubCategory("Vegetables", "onion");
		boolean actualAlertMsg=subcategorypage.checkAlreadyExistAlertMsg(Constants.SUBCAT_DUPLICATION_MSG);
		Assert.assertTrue(actualAlertMsg);
	}
	
	@Test
	public void verifyAdminIsAbleToDeleteSubCategory()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		subcategorypage=dashboardpage.navigateToSubCategoryCard();
		subcategorypage.createNewSubCategory("Vegetables", "Radish");
		subcategorypage.clickOnSaveButton();
		subcategorypage.deleteSubCategory();
		boolean actualDeleteAlert=subcategorypage.checkDeleteSuccessAlertMsg(Constants.SUBCAT_DELETION_SUCCESS_MSG);
		Assert.assertTrue(actualDeleteAlert);
	}
}
