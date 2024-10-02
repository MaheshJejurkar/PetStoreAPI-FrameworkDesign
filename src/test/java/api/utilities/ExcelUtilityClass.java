package api.utilities;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtilityClass {
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	public String ct;
	String filePath;
	
	public ExcelUtilityClass(String filePath) {
		this.filePath = filePath;
	}

	public int getRowCount(String sheetName) throws Exception {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		workbook.close();
		fis.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNo) throws Exception {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int cellCount = sheet.getRow(rowNo).getPhysicalNumberOfCells();
		workbook.close();
		fis.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rowNo, int cellNo) throws IOException {
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(cellNo);
		String data = cell.toString();
		workbook.close();
		fis.close();
		return data;
	}
}