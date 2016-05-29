package pPkgKMV;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExcelUtils {
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFRow ExcelWRow;
	private static XSSFCell ExcelCell;
	
	//Open Excel File
	@Test(priority=0)
	@Parameters({"sFilePath","sSheetName"})
	public static void fOpenExcelFile(String sFilePath,String sSheetName) throws Exception{
		FileInputStream sFile = new FileInputStream(sFilePath);
		ExcelWBook = new XSSFWorkbook(sFile);
		ExcelWSheet = ExcelWBook.getSheet(sSheetName);
		System.out.println("In File Open");
		
	}
	
	
	
	//@DataProvider(name="XLTestData")
	public static Object[][] getXlData() throws Exception{
		
		try{
			System.out.println("LastRow:"+ExcelWSheet.getLastRowNum());
			int iRowCount = ExcelWSheet.getLastRowNum();
			
			Object[][] ReturnXlData = new Object[iRowCount+1][2];
			for (int iLoop=0;iLoop<=iRowCount;iLoop++)
			{
				ExcelWRow = ExcelWSheet.getRow(iLoop);
				int iColCount = ExcelWRow.getLastCellNum();
				System.out.println("iColCount is"+ iColCount);
				for (int jLoop=0;jLoop<iColCount;jLoop++){
				System.out.println("Value is"+ getCellData(iLoop,jLoop));
				ReturnXlData[iLoop][jLoop]=getCellData(iLoop,jLoop);
				}
			}
			return ReturnXlData;
		}catch (Exception e){
			throw (e);
	
		}
		
	}
	
	//Read Cell Data
	public static String getCellData (int iRowNum, int iColNum) throws Exception{
		try{
			ExcelCell = ExcelWSheet.getRow(iRowNum).getCell(iColNum);
			int iCellType = ExcelCell.getCellType();
			if(iCellType==3){
				return "";
			}else {
				String CellData  = ExcelCell.getStringCellValue();
				return CellData;
				}
		}catch (Exception e){
			throw (e);
		}
	}
	//set CellData
	public static void setCellData(int iRowNum, int iColNum,String sValue,String sFilePath) throws Exception{
	try {
		ExcelWRow = ExcelWSheet.getRow(iRowNum);
		ExcelCell = ExcelWSheet.getRow(iRowNum).getCell(iColNum,ExcelWRow.RETURN_NULL_AND_BLANK);
		if(ExcelCell==null){
			ExcelCell = ExcelWRow.createCell(iColNum);
			ExcelCell.setCellValue(sValue); 
		}
		else{
			ExcelCell.setCellValue(sValue); 
		}
		//Output file
		FileOutputStream sFileOut = new FileOutputStream(sFilePath);
		ExcelWBook.write(sFileOut);
		sFileOut.flush();
		sFileOut.close();
		}catch (Exception e){
		throw(e);
		}
		}
	//Get TotalRowsCount
	public static int getRowCount() throws Exception{
		try{
		int iRowCount = ExcelWSheet.getPhysicalNumberOfRows();
		return iRowCount;
		}catch (Exception e)
		{
			throw (e);
		}
	}
	}
	

