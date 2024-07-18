package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sevenmartsupermarket.base.Base;
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
		dashboardpage= new DashboardPage(driver);
		adminuserpage=new AdminUserPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		String actualHeader=adminuserpage.getHeader();
		System.out.println(actualHeader);
		String expectedHeader="Admin Users";
		Assert.assertEquals(actualHeader, expectedHeader);
	}
	@Test(groups = "regression")
	public void verifyNewButtonisclickable()
	{		
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		adminuserpage=new AdminUserPage(driver);	
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
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
		dashboardpage= new DashboardPage(driver);
		adminuserpage=new AdminUserPage(driver);	
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserpage.clickNewButton();		
		adminuserpage.createNewAdminUser(userName, password,userType);
		String actualMessage=adminuserpage.getSuccessAlertMsg();
		System.out.println(actualMessage);
		String expectedMessage="User Created Successfully";
		Assert.assertTrue(actualMessage.contains(expectedMessage));	
	}
	
	@Test
	public void verifySuccessfulUserDeletion()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		adminuserpage=new AdminUserPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserpage.clickNewButton();
		String userName="Henna "+GeneralUtility.get_RandomFirstName();
		adminuserpage.createNewAdminUser(userName, "1111", "admin");
		adminuserpage.clickOnDeleteLink();
		String actualMsg=adminuserpage.getDeleteAlertMsg();
		System.out.println(actualMsg);
		String expectedMsg="User Deleted Successfully";
		Assert.assertTrue(actualMsg.contains(expectedMsg));
	}
	
	@Test
	public void verifyUnsuccessfulDuplicateUserCreation()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		adminuserpage=new AdminUserPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserpage.clickNewButton();
		adminuserpage.createNewAdminUser("Henna1", "1111", "admin");
		String actualMessage=adminuserpage.getUnsuccessfulAlertMsg();
		System.out.println(actualMessage);
		String expectedMessage="Username already exists";
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		
	}
	@Test
	public void verifyAdminIsAbleToSearchUsers()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		adminuserpage=new AdminUserPage(driver);
		
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserpage.searchForUsers("Henna1");
		String actualResult=adminuserpage.getSearchResult();
		System.out.println(actualResult);
		String expectedResult=("Henna1");
		Assert.assertTrue(actualResult.contains(expectedResult));
	}
	@Test
	public void verifyUserIsAbleToEditAndUpdateDetailsSuccessfully()
	{
		loginpage= new LoginPage(driver);
		dashboardpage= new DashboardPage(driver);
		adminuserpage=new AdminUserPage(driver);
		loginpage.login();
		dashboardpage.navigateToCard("Admin Users");
		adminuserpage.clickNewButton();
		String userName="Henna "+GeneralUtility.get_RandomFirstName();
		adminuserpage.createNewAdminUser(userName, "1111", "admin");
		adminuserpage.clickOnEditLink(userName);
		adminuserpage.editUserNameAndUpdate("Hawwa");
		String actual=adminuserpage.getSuccessUpdateMsg();
		String expected="User Updated Successfully";
		Assert.assertTrue(actual.contains(expected));
	}
	}
