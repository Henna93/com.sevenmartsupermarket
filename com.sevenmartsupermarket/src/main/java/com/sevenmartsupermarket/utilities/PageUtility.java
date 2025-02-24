package com.sevenmartsupermarket.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class PageUtility {
	WebDriver driver;
	JavascriptExecutor js;

	public PageUtility(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public void mouseMove(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.moveToElement(element).build().perform();

	}

	public void select_Index(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void select_ByVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);

	}
	public void select_ByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);

	}

	public void scrollAndClick(WebElement element) {
		int index = 0;
		while (!isClicked(element)) {
			js.executeScript("window.scrollBy(0," + index + ")");
			index = index + 3;

		}

	}

	public boolean isClicked(WebElement element) {
		try {
			element.click();
			return true;

		} catch (Exception e) {

			return false;
		}
	}
	public void jsClick(WebElement element)
	{
		js.executeScript("arguments[0].click();",element);
		
	}
	
}
