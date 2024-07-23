package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.constants.Constants;
import com.sevenmartsupermarket.pages.AdminUserPage;
import com.sevenmartsupermarket.pages.DashboardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.ExcelReader;
import com.sevenmartsupermarket.utilities.GeneralUtility;


public class AdminUserTest extends Base{
	LoginPage loginpage;
	DashboardPage dashboardpage;
	AdminUserPage adminuserpage;
	ExcelReader excelreader=new ExcelReader();
	
	@Test(groups = "smoke")
	public void verifyAdminUserHeader()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		adminuserpage=dashboardpage.navigateToAdminUserCard();
		String actualHeader=adminuserpage.getHeader();
		Assert.assertEquals(actualHeader, Constants.ADMINUSER_HEADER);
	}
	@Test(groups = "regression")
	public void verifyNewButtonisclickable()
	{		
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		adminuserpage=dashboardpage.navigateToAdminUserCard();
		adminuserpage.clickNewButton();
		boolean actualResult=adminuserpage.checkForUserNameField();
		Assert.assertTrue(actualResult);
		}

	@Test(groups = {"regression","smoke"})
	public void verifySuccessfulUserCreation()
	{
		excelreader.setExcelFile("Logindata","credentials");
		String userName=excelreader.getCellData(2, 0)+GeneralUtility.get_RandomFirstName();
		String password=excelreader.getCellData(2, 1);
		String userType=excelreader.getCellData(2,2);
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		adminuserpage=dashboardpage.navigateToAdminUserCard();
		adminuserpage.clickNewButton();		
		adminuserpage.createNewAdminUser(userName, password,userType);
		String actualSuccessMessage=adminuserpage.getSuccessAlertMsg();
		Assert.assertTrue(actualSuccessMessage.contains(Constants.USER_CREATION_SUCCESS_MSG));	
	}
	
	@Test
	public void verifySuccessfulUserDeletion()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		adminuserpage=dashboardpage.navigateToAdminUserCard();
		adminuserpage.clickNewButton();
		String userName="Henna "+GeneralUtility.get_RandomFirstName();
		adminuserpage.createNewAdminUser(userName, "1111", "admin");
		adminuserpage.clickOnDeleteLink();
		String actualDeleteMsg=adminuserpage.getDeleteAlertMsg();
		Assert.assertTrue(actualDeleteMsg.contains(Constants.USER_DELETION_SUCCESS_MSG));
	}
	
	@Test
	public void verifyUnsuccessfulDuplicateUserCreation()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		adminuserpage=dashboardpage.navigateToAdminUserCard();
		adminuserpage.clickNewButton();
		adminuserpage.createNewAdminUser("Henna1", "1111", "admin");
		String actualDuplicateAlertMessage=adminuserpage.getUnsuccessfulAlertMsg();
		Assert.assertTrue(actualDuplicateAlertMessage.contains(Constants.USER_DUPLICATION_MSG));
		
	}
	@Test
	public void verifyAdminIsAbleToSearchUsers()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		adminuserpage=dashboardpage.navigateToAdminUserCard();
		adminuserpage.searchForUsers("Henna1");
		boolean actualResult=adminuserpage.getSearchResult("Henna1");
		Assert.assertTrue(actualResult);
	}
	@Test
	public void verifyUserIsAbleToEditAndUpdateDetailsSuccessfully()
	{
		loginpage= new LoginPage(driver);
		dashboardpage=loginpage.login();
		adminuserpage=dashboardpage.navigateToAdminUserCard();
		adminuserpage.clickNewButton();
		String userName="Henna "+GeneralUtility.get_RandomFirstName();
		adminuserpage.createNewAdminUser(userName, "1111", "admin");
		adminuserpage.clickOnEditLink(userName);
		adminuserpage.editUserNameAndUpdate("Hawwa");
		String actualSuccessMsg=adminuserpage.getSuccessUpdateMsg();
		Assert.assertTrue(actualSuccessMsg.contains(Constants.USER_UPDATION_MSG));
	}
	}
