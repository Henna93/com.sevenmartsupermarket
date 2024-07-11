package DataProviders;

import org.testng.annotations.DataProvider;

import com.sevenmartsupermarket.utilities.ExcelReader;

public class DataProviderEx {
	ExcelReader excelreader=new ExcelReader();
	@DataProvider(name="credentials")
	public Object[][] loginDetails()
	{
		excelreader.setExcelFile("loginCredentials", "sheet1");
		return excelreader.getMultidimentionalData(3, 3);
	}

}
