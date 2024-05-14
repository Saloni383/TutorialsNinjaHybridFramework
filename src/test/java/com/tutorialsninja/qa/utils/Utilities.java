package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 5;
	private static final int CellType = 0;

	public static String generateTimeStamp() {
		Date date = new Date();
		String currentDate = date.toString().replace(" ", "-").replace(":", "-");
		return "salonikaushik" + currentDate + "@gmail.com";
	}

	public static String generateDate() {
		Date date = new Date();
		String currentDate = date.toString().replace(" ", "-").replace(":", "-");
		return currentDate;
	}

	public static Object getDataFromExcel(String sheetname) throws IOException {
		File ExcelFile = new File(
				"C:\\Users\\saloni.kaushik\\HybridTestNGFramework\\TutorialsNinjaProject\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinja_Testdata.xlsx");
		FileInputStream fisExcel = new FileInputStream(ExcelFile);

		XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell col = row.getCell(j);
				CellType cellType = col.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = col.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) col.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i][j] = col.getBooleanCellValue();
					break;

				}
			}
		}
		return data;
	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		//File srcScreenshot = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
		File srcScreenshotfile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//File destFile= new File("C:\\Users\\saloni.kaushik\\HybridTestNGFramework\\TutorialsNinjaProject\\Screenshots"+ testName + Utilities.generateDate() + ".png");
		
		//FileUtils.copyDirectory(srcScreenshotfile, destFile);
		String destinationFilePath = ("C:\\Users\\saloni.kaushik\\HybridTestNGFramework\\TutorialsNinjaProject\\Screenshots"+ testName + Utilities.generateDate() + ".png");
		
		try {
			FileHandler.copy(srcScreenshotfile, new File(destinationFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationFilePath;
	}

}
