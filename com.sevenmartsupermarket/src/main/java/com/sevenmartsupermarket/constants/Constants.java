package com.sevenmartsupermarket.constants;

public class Constants {
	
	public static final long IMPLICIT_WAIT=10;
	
	
	public static final String CONFIG_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties";
	public static final String EXCEL_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\resources\\ExcelFile\\";
	public static final String SCREENSHOT_FILE_PATH=System.getProperty("user.dir")+"\\screenshots\\";
	public static final String EXTENT_FILE_PATH=System.getProperty("user.dir")+"\\ExtentReport";
	public static final String IMAGE_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\resources\\Image\\cap.jpeg";

	/**Expected Results**/
	public static final String INVALID_LOGIN_ALERT="Alert!";
	public static final String WEBSITE_HEADER="7rmart supermarket";
	public static final String SIGN_IN_MSG="Sign in to start your session";
	
	public static final String ADMINUSER_HEADER="Admin Users";
	public static final String USER_CREATION_SUCCESS_MSG="User Created Successfully";
	public static final String USER_DELETION_SUCCESS_MSG="User Deleted Successfully";
	public static final String USER_DUPLICATION_MSG="Username already exists";
	public static final String USER_UPDATION_MSG="User Updated Successfully";
	
	public static final String SUBCATEGORY_HEADER="List Sub Categories";
	public static final String SUBCAT_CREATION_SUCCESS_MSG="Sub Category Created Successfully";
	public static final String SUBCAT_DUPLICATION_MSG="Sub Category already exists.";
	public static final String SUBCAT_DELETION_SUCCESS_MSG="Sub Category Deleted Successfully";
}
