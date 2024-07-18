package com.sevenmartsupermarket.pages;

import java.io.File;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sevenmartsupermarket.constants.Constants;
import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class SubCategoryPage {
	WebDriver driver;
	PageUtility pageutility;
	GeneralUtility generalutility = new GeneralUtility();
	Properties properties = new Properties();
	File f;

	@FindBy(xpath = "//h1[@class='m-0 text-dark']")
	private WebElement subCategoryHeader;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//select[@id='cat_id']")
	private WebElement categoryField;
	@FindBy(xpath = "//input[@id='subcategory']")
	private WebElement subCategoryField;
	@FindBy(xpath = "//input[@id='main_img']")
	private WebElement chooseFileField;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successAlertMsg;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(xpath = "//select[@id='un']")
	private WebElement searchCategoryField;
	@FindBy(xpath = "//input[@class='form-control']")
	private WebElement searchSubCategoryField;
	@FindBy(xpath = "//button[@class='btn btn-danger btn-fix']")
	private WebElement searchSubCategoryButton;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[1]")
	private WebElement searchedItem;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement alreadyExistAlert;
	@FindBy(xpath = "//p[text()='Sub Category']")
	private WebElement subCategoryOption;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[5]//a[2]")
	private WebElement deleteButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement deleteAlertMsg;

	public SubCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getHeader() {
		return subCategoryHeader.getText();
	}

	public void clickOnNewButton() {
		newButton.click();
	}

	public void selectCategory(String visibleText) {
		pageutility = new PageUtility(driver);

		pageutility.select_ByVisibleText(categoryField, visibleText);
	}

	public void enterSubCategory(String vegetable) {
		subCategoryField.sendKeys(vegetable);
	}

	public void chooseFileImg() {
		String imgDestination = Constants.IMAGE_FILE_PATH;
		File filePath = new File(imgDestination);
		String absolutePath = filePath.getAbsolutePath();
		chooseFileField.sendKeys(absolutePath);

	}

	public void clickOnSaveButton() {
		pageutility = new PageUtility(driver);
		pageutility.scrollAndClick(saveButton);

	}

	public void createNewSubCategory(String visibleText, String vegetable) {
		clickOnNewButton();
		selectCategory(visibleText);
		enterSubCategory(vegetable);
	}

	public String getSuccessMsgNewSubCategory() {
		return successAlertMsg.getText();
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void selectSearchCategory(String visibleText) {
		pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(searchCategoryField, visibleText);
	}

	public void enterSearchSubCatField(String item) {
		searchSubCategoryField.sendKeys(item);
	}

	public void clickOnSubCatSearchButton() {
		searchSubCategoryButton.click();

	}

	public void checkSearchButton(String visibleText, String item) {
		clickOnSearchButton();
		selectSearchCategory(visibleText);
		enterSearchSubCatField(item);
		clickOnSubCatSearchButton();
	}

	public boolean checkSearchItemPresent(String expectedResult) {
		return generalutility.is_TextPresent(searchedItem, expectedResult);
	}

	public void createDuplicateSubCategory(String visibleText, String vegetable) {
		clickOnNewButton();
		selectCategory(visibleText);
		enterSubCategory(vegetable);
		clickOnSaveButton();
	}

	public boolean checkAlreadyExistAlertMsg(String expectedResult) {
		return generalutility.is_TextPresent(alreadyExistAlert, expectedResult);
	}

	public void deleteSubCategory() {
		subCategoryOption.click();
		deleteButton.click();
		driver.switchTo().alert().accept();
	}

	public boolean checkDeleteSuccessAlertMsg(String expectedResult) {
		return generalutility.is_TextPresent(deleteAlertMsg, expectedResult);
	}

}
