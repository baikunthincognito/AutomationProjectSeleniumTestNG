package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public static String generateRandomMailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "baikunth.shukla." + timestamp + "@gmail.com";
	}

	public static String RequiredString(int n)
	{
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	StringBuilder s = new StringBuilder(n);
	int y;
	for ( y = 0; y < n; y++) 
	{
	int index = AlphaNumericString.length();
	s.append(AlphaNumericString.charAt(index));
	}
	return s.toString();
	}

	public static String randomTextGenerator(String text, int n) {

		String randomText = RequiredString(n);
		
		return text+randomText;
	}

	public static String generateRandomNumber(int n)
	{
		int i=0, factor=1;
		while(i<n)
		{
			factor = factor*10;
			i++;
		}
		float ranNumber = (float)Math.random();		
		float Number = ranNumber*factor;
		int num = (int) Number;
		
		
		return Integer.toString(num);
	}
	
	
	// POI - Excel data Read
	public static Object[][] getTestDataFromExcel(String sheetName)
	{
		//Get the file
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorials\\qa\\data\\TutorialNinjaTestData.xlsx");
		
		// Initialize the workbook
		XSSFWorkbook wb = null;
		
		// Get Object To Read Input Stream, Handle workbook
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			wb = new XSSFWorkbook(fisExcel);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		//Get Sheet via WorkBook [wb]
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		// No. of rows [Filled] to drive the loop
		int rows = sheet.getLastRowNum();
		// No. of columns [Filled] to drive the loop
		int cols = sheet.getRow(0).getLastCellNum();
		
		//Declare Setting Up Object length
		Object[][] data = new Object[rows][cols];
		
		//Get row and then corresponding each cell data
		for(int i=0; i<rows; i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0; j<cols; j++)
			{
				XSSFCell cell = row.getCell(j);
				
				// Type of Cell
				
				CellType cellType = cell.getCellType();
				
				// switch case
				switch(cellType)
				{
				case STRING : 
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC : 
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN : 
					data[i][j] = cell.getBooleanCellValue();
					break;
				
				default:
					System.out.println("No Matching Cell Type. Please refer the Java documentation.");
					break;
				}
			}	
		}
		
		return data;
	}
}
