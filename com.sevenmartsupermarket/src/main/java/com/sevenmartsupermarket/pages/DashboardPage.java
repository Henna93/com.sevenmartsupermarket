package com.sevenmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.WaitUtility;

public class DashboardPage {
	WebDriver driver;
	GeneralUtility generalutility = new GeneralUtility();
	WaitUtility waitutility;

	@FindBy(xpath = "//span[@class='brand-text font-weight-light']")
	private WebElement header;
	@FindBy(xpath = "//a[@class='d-block']")
	private WebElement profileName;
	@FindBy(xpath = "//section//div[@class='container-fluid']//a")
	private List<WebElement> dashBoardCards;
	@FindBy(xpath = "//section//div[@class='container-fluid']//div//p")
	private List<WebElement> cardNames;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath="(//a[@href='#'])[2]")
	private WebElement adminLink;
	@FindBy(xpath="(//a[@class='dropdown-item'])[2]")
	private WebElement logoutLink;
	@FindBy(xpath="//p[@class='login-box-msg']")
	private WebElement signInMsg;
	@FindBy(xpath = "//input[@name='username']")
	private WebElement userNameField;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String getWebSiteHeader()
	{
		return header.getText();
	}

	public String getProfileName()  {
		
		return profileName.getText();
	}

	public void navigateToCard(String name) {
		int index = 0;
		for (index = 0; index < cardNames.size(); index++) {
			String infoName = cardNames.get(index).getText();

			if (infoName.equalsIgnoreCase(name)) {
				break;
			}

		}
		dashBoardCards.get(index).click();
	}
	public boolean checkForNewButton(String expectedResult)
	{
		
		return generalutility.is_TextPresent(newButton, expectedResult);
	}
	public void clickOnLogout()
	{
		adminLink.click();
		logoutLink.click();
	}
	public boolean checkSignInMsg(String expectedResult)
	{
		waitutility=new WaitUtility(driver);
		waitutility.waitForElementToBeClickable(userNameField, 30);
		return generalutility.is_TextPresent(signInMsg, expectedResult);
	}
}
