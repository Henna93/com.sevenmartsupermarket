package com.sevenmartsupermarket.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;


public class SubCategoryPage {
	WebDriver driver;
	PageUtility pageutility = new PageUtility(driver);
	GeneralUtility generalutility=new GeneralUtility();
	
	
	@FindBy(xpath="//h1[@class='m-0 text-dark']")WebElement subCategoryHeader;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-danger']")WebElement newButton;
	@FindBy(xpath="//select[@id='cat_id']")WebElement categoryField;
	@FindBy(xpath="//input[@id='subcategory']")WebElement subCategoryField;
	@FindBy(xpath="//input[@id='main_img']")WebElement chooseFileField;
	@FindBy(xpath="//button[@class='btn btn-danger']")WebElement subCatSaveButton;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-primary']")WebElement searchButton;
	@FindBy(xpath="//select[@id='un']")WebElement searchCategoryField;
	@FindBy(xpath="//input[@class='form-control']")WebElement searchSubCategoryField;
	@FindBy(xpath="//button[@class='btn btn-danger btn-fix']")WebElement searchSubCategoryButton;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[1]")WebElement searchedItem;
	
	public SubCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getHeader()
	{
		return subCategoryHeader.getText();
	}
	public void clickOnNewButton()
	{
		newButton.click();
	}
	public void selectCategory(String visibleText)
	{
		pageutility.select_ByVisibleText(categoryField, visibleText);
	}
	public void enterSubCategory()
	{
		subCategoryField.sendKeys("capsicum");
	}
	public void chooseFileImg()
	{
		String path="C:\\Users\\shaha\\Desktop\\Selenium\\selenium img\\cap.jpeg";
		chooseFileField.sendKeys(path);
	}
	public void clickOnSaveButton()
	{
		subCatSaveButton.click();
	}
	public void clickOnSearchButton()
	{
		searchButton.click();
	}
	public void selectSearchCategory(String visibleText)
	{
		pageutility.select_ByVisibleText(searchCategoryField, visibleText);
	}
	public void enterSearchSubCatField()
	{
		searchSubCategoryField.sendKeys("onion");
	}
	public void clickOnSubCatSearchButton()
	{
		searchSubCategoryButton.click();
	}
	public boolean CheckSearchItemPresent(String expectedResult)
	{
		return generalutility.is_TextPresent(searchedItem,expectedResult);
	}
}
