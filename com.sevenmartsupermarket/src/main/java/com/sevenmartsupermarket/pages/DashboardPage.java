package com.sevenmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	WebDriver driver;
	
	@FindBy(xpath="//a[@class='d-block']")
	WebElement profileName;
	@FindBy(xpath="//section//div[@class='container-fluid']//a")
	List<WebElement> dashBoardCards;
	@FindBy(xpath="//section//div[@class='container-fluid']//div//p")
	List<WebElement> cardNames;
	
	public DashboardPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String getProfileName()
	{
		return profileName.getText();
	}
	public void navigateToCard(String name)
	{
	int index=0;
	for(index=0;index<cardNames.size();index++)
	{
		String infoName=cardNames.get(index).getText();
		
		
		if(infoName.equalsIgnoreCase(name))
		{
			break;
		}
			
	}
	dashBoardCards.get(index).click();
}
}
