package com.demowebshop.genericlibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataUtility {
	public String getDataFromProperties(String key)  {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(FilePaths.PROPERTIES_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public String getDataFromExcel(String sheetName, int rowIndex, int cellIndex)
			throws FileNotFoundException, IOException {
		return WorkbookFactory.create(new FileInputStream(FilePaths.EXCEL_PATH)).getSheet(sheetName).getRow(rowIndex)
				.getCell(cellIndex).toString();
	}

	public Object[][] getAllDataFromExcel(String sheetName) {

		try {
			Sheet sheet = WorkbookFactory.create(new FileInputStream(FilePaths.EXCEL_PATH)).getSheet(sheetName);
			int rowCount = sheet.getPhysicalNumberOfRows();
			Object[][] data = new Object[rowCount - 1][];
			for (int i = 1; i < rowCount; i++) {
				int cellCount = sheet.getRow(i).getPhysicalNumberOfCells();
				data[i - 1] = new Object[cellCount];
				for (int j = 0; j < cellCount; j++) {
					data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
				}
			}
			return data;
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
