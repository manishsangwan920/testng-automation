package configreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import settings.ObjectRepo;
import com.relevantcodes.extentreports.LogStatus;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class ExcelReader {
	
	
	public static Sheet readRunManager() throws Exception {
//		Sheet Instance =ReadExcel(System.getProperty("user.dir")+"/RunManager.xlsm", "InstanceRunSheet");
//		String InstanceName = Instance.getRow(1).getCell(0).getStringCellValue();
		try {
			return ReadExcel(System.getProperty("user.dir")+"/RunManager.xlsm", "Testcase");
		}catch(Exception e) {
			System.out.println("Unable to read RunManager sheet");
			return null;
		}
	}
	
	public static Sheet readInstance(){
		try {
			return ReadExcel(System.getProperty("user.dir")+"/RunManager.xlsm", "InstanceRunSheet");
		}catch(Exception e) {
			System.out.println("Unable to read RunManager sheet");
			return null;
		}
	}
	
	
	public static Sheet readTestDate() {
		try {
			if(ObjectRepo.Environment.equals("QA"))
				return ReadExcel(System.getProperty("user.dir")+"/QA_TestData.xlsx", "TestData");
			else if(ObjectRepo.Environment.equals("Prod"))
				return ReadExcel(System.getProperty("user.dir")+"/Prod_TestData.xlsx", "TestData");
			else
				System.out.println("Select QA or Prod as environment in RunManager sheet");
				return null;
		}catch(Exception e) {
			System.out.println( "Unable to Read Test Data sheet");
			return null;
		}
	}
	
	public static Sheet readTestFlow() {
		try {
			if(ObjectRepo.Environment.equals("QA"))
				return ReadExcel(System.getProperty("user.dir")+"/QA_TestData.xlsx", "TestCases");
			else if(ObjectRepo.Environment.equals("Prod"))
				return ReadExcel(System.getProperty("user.dir")+"/Prod_TestData.xlsx", "TestCases");
			else
				System.out.println("Select QA or Prod as environment in RunManager sheet");
				return null;
		}catch(Exception e) {
			System.out.println("Unable to read TestData sheet");
			return null;
		}
	}
	
	
	public static Sheet readNewTestFlow() {
		try {
			if(ObjectRepo.Environment.equals("QA"))
				return ReadExcel(System.getProperty("user.dir")+"/QA_TestData.xlsx", "NewTestCases");
			else if(ObjectRepo.Environment.equals("Prod"))
				return ReadExcel(System.getProperty("user.dir")+"/Prod_TestData.xlsx", "NewTestCases");
			else
				System.out.println("Select QA or Prod as environment in RunManager sheet");
				return null;
		}catch(Exception e) {
			System.out.println("Unable to read TestData sheet");
			return null;
		}
	}
	
	public static Sheet readQuestions() {
		try {
			if(ObjectRepo.Environment.contains("QA"))
				return ReadExcel(System.getProperty("user.dir")+"/QA_TestData.xlsx", "Questions");
			else if(ObjectRepo.Environment.contains("Prod"))
				return ReadExcel(System.getProperty("user.dir")+"/Prod_TestData.xlsx", "Questions");
			else
				System.out.println("Select QA or Prod as environment in RunManager sheet");
				return null;
		}catch(Exception e) {
			System.out.println("Unable to read Questions sheet");
			return null;
		}
	}
	
	public static Sheet ReadExcel(String filepath, String sheetName) throws Exception {
		File excelFile =    new File(filepath);
		try {
				FileInputStream inputStream = new FileInputStream(excelFile);
				Workbook excelworkbook = null;
				String fileExtensionName = filepath.substring(filepath.indexOf("."));
				if(fileExtensionName.equals(".xlsx") || fileExtensionName.equals(".xlsm")){
					excelworkbook = new XSSFWorkbook(inputStream);
				}else if(fileExtensionName.equals(".xls")){
					excelworkbook = new HSSFWorkbook(inputStream);
				}
				Sheet sheet = excelworkbook.getSheet(sheetName);
				return sheet;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
		}
	
	public static void updateExcel(String filepath, String sheetName, int RowNo, int ColNo, String Value) throws Exception {
		File excelFile =    new File(filepath);
		try {
				FileInputStream inputStream = new FileInputStream(excelFile);
				Workbook excelworkbook = null;
				String fileExtensionName = filepath.substring(filepath.indexOf("."));
				if(fileExtensionName.equals(".xlsx")){
					excelworkbook = new XSSFWorkbook(inputStream);
				}else if(fileExtensionName.equals(".xls")){
					excelworkbook = new HSSFWorkbook(inputStream);
				}
				Sheet sheet = excelworkbook.getSheet(sheetName);
				try {
				sheet.getRow(RowNo).getCell(ColNo).setCellValue(Value);
				}catch(Exception e) {
					System.out.println("Creating new cell");
					sheet.getRow(RowNo).createCell(ColNo).setCellValue(Value);
				}
				
	            inputStream.close();
	            FileOutputStream outputStream = new FileOutputStream(excelFile);
	            excelworkbook.write(outputStream);
	            outputStream.close();
				 
			} catch (Exception e) {
				System.out.println("Unable to update Test Data file Please close the file, if open"+e.getMessage());
				e.printStackTrace();
			}
		}
	
	public static int searchExcel(String filepath, String sheetName, int col, String Value) throws Exception {
		File excelFile =    new File(filepath);
		try {
			FileInputStream inputStream = new FileInputStream(excelFile);
			Workbook excelworkbook = null;
			String fileExtensionName = filepath.substring(filepath.indexOf("."));
			if(fileExtensionName.equals(".xlsx")||fileExtensionName.equals(".xlsm")){
				excelworkbook = new XSSFWorkbook(inputStream);
			}else if(fileExtensionName.equals(".xls")){
				excelworkbook = new HSSFWorkbook(inputStream);
			}
			Sheet sheet = excelworkbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            for(int i =1; i<=rowCount; i++) {
            	if(sheet.getRow(i).getCell(col).toString().equals(Value)) {
            		return i;
            	}
            }
            System.out.println("Unable to find keyword in the file");
            return 0;
		} catch (Exception e) {
				System.out.println("Unable to find keyword in the file");
				e.printStackTrace();
				return 0;
			}
		}
	
	public static List<String> getColumnasList(Sheet sheet, String ColName){
		List<String> colvalues = new ArrayList<String>();
		Row headerRow = sheet.getRow(0);
		for(int i = 0; i<headerRow.getLastCellNum(); i++) {
			try {
				if(headerRow.getCell(i).toString().trim().equalsIgnoreCase(ColName.trim())) {
					try {
						int j=1;
						while(true) {
							String value = sheet.getRow(j).getCell(i).toString();
							if(value.equals("")) {
								System.out.println("End of List");
								break;
							}
							colvalues.add(value.trim());
							j++;
						}
					}catch(Exception e) {
						System.out.println("End of List");
					}
					break;
				}else {
				}
			}catch(Exception e) {
			
			}
			
		}
		return colvalues; 
	}
	
	public static void createColUsingList(String filepath, String sheetName, int ColNo, List<String> colvalues) throws Exception {
		File excelFile =    new File(filepath);
		try {
				FileInputStream inputStream = new FileInputStream(excelFile);
				Workbook excelworkbook = null;
				String fileExtensionName = filepath.substring(filepath.indexOf("."));
				if(fileExtensionName.equals(".xlsx")){
					excelworkbook = new XSSFWorkbook(inputStream);
				}else if(fileExtensionName.equals(".xls")){
					excelworkbook = new HSSFWorkbook(inputStream);
				}
				Sheet sheet = excelworkbook.getSheet(sheetName);
				int i = 0;
				for(String value : colvalues) {
					try {
						sheet.getRow(i).getCell(ColNo).setCellValue(value);
						}catch(Exception e) {
							try {

								System.out.println("Creating new cell");
								sheet.getRow(i).createCell(ColNo).setCellValue(value);
							}catch(Exception e1) {
								sheet.createRow(i).createCell(ColNo).setCellValue(value);
							}
						}
					i++;
				}
				
				
	            inputStream.close();
	            FileOutputStream outputStream = new FileOutputStream(excelFile);
	            excelworkbook.write(outputStream);
	            outputStream.close();
				 
			} catch (Exception e) {
				System.out.println("Unable to update Test Data file Please close the file, if open"+e.getMessage());
				e.printStackTrace();
			}
		}

}
