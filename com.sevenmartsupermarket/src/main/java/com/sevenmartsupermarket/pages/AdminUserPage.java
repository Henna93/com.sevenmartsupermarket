package com.sevenmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class AdminUserPage {
	WebDriver driver;
	PageUtility pageutility = new PageUtility(driver);
	GeneralUtility generalutility=new GeneralUtility();

	@FindBy(xpath = "//h1[contains(text(),'Admin Users')]")
	private WebElement header;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//input[@id='username']")
	private WebElement usernameField;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordField;
	@FindBy(xpath = "//select[@id='user_type']")
	private WebElement userTypeField;
	@FindBy(xpath = "//button[@name='Create']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successAlertMsg;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[5]//a[3]")
	private WebElement deleteLink;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement deleteAlertMsg;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement unsuccessAlertMsg;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-primary']")
	WebElement searchField;
	@FindBy(xpath="//button[@name='Search']")
	WebElement searchButton;
	@FindBy(xpath="//input[@id='un']")
	WebElement searchFieldUsername;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[1]")
	WebElement searchResult;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")List<WebElement> tableNames;
	@FindBy(xpath="//button[@class='btn btn-block-sm btn-info']")WebElement updateButton;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")WebElement successUpdateMsg;

	public AdminUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getHeader() {
		return header.getText();
	}

	public void clickNewButton() {
		newButton.click();
	}

	public void enterUserName(String userName) {
		usernameField.sendKeys(userName);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void selectUserType(String value) {
		pageutility.select_ByValue(userTypeField, value);
	}

	public void clickOnSaveButton() {
		saveButton.click();
	}

	public void createNewAdminUser(String userName, String password, String userType) {
		enterUserName(userName);
		enterPassword(password);
		selectUserType(userType);
		clickOnSaveButton();
	}

	public String getSuccessAlertMsg() {
		return successAlertMsg.getText();
	}

	public void clickOnDeleteLink() {
		deleteLink.click();
		driver.switchTo().alert().accept();
	}

	public String getDeleteAlertMsg() {
		return deleteAlertMsg.getText();
	}

	public String getUnsuccessfulAlertMsg() {
		return unsuccessAlertMsg.getText();
	}
	
	public void searchForUsers(String userName)
	{
		searchField.click();
		searchFieldUsername.sendKeys(userName);
		searchButton.click();
	}
	public String getSearchResult()
	{
		return searchResult.getText();
	}
	public void clickOnEditLink(String userName)
	{
		List<String>names=new ArrayList<String>();
		names=generalutility.get_TextOfElements(tableNames);
		int index=0;
		for(index=0;index<names.size();index++)
		{
			if(names.get(index).equals(userName))
			{
				index++;
				break;
			}
		}
		WebElement editLink=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+index+"]//td[5]//a[2]"));
		editLink.click();
	}
	public void editUserNameAndUpdate()
	{
		usernameField.sendKeys(" Hawwa");
		updateButton.click();
	}
	public String getSuccessUpdateMsg()
	{
		return successUpdateMsg.getText();
	}
}
