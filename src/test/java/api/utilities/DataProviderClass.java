package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "getUserData")
	public String[][] getUserData() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\testdata\\PetStore-UserAPI-TestData.xlsx";

		ExcelUtilityClass excelUtility = new ExcelUtilityClass(filePath);

		int rowCount = excelUtility.getRowCount("Sheet1");
		int columnCount = excelUtility.getCellCount("Sheet1", 1);

		String[][] userdata = new String[rowCount - 1][columnCount];
		for (int r = 1; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				userdata[r - 1][c] = excelUtility.getCellData("Sheet1", r, c);
			}
		}
		return userdata;
	}
	
	@DataProvider(name = "getUsername")
	public String[] getUsername() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\testdata\\PetStore-UserAPI-TestData.xlsx";

		ExcelUtilityClass excelUtility = new ExcelUtilityClass(filePath);
		int rowCount = excelUtility.getRowCount("Sheet1");

		String[] username = new String[rowCount - 1];
		for (int r = 1; r < rowCount; r++) {
				username[r - 1] = excelUtility.getCellData("Sheet1", r, 1);
		}
		return username;
	}

}
