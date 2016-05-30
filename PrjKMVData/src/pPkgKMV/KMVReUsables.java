package pPkgKMV;

import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class KMVReUsables {
	public static WebDriver driver = null;
	
	public static void fLogintoNavigate(String Uid,String Pwd) throws Exception{
		try
		{
		ExcelDataProvider.setup("Chrome");
		driver=ExcelDataProvider.driver; 
		driver.get("http://www.adilife.com/memlogin.asp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Login
		PageObjects.txtUid(driver).sendKeys(Uid);
		PageObjects.txtPwd(driver).sendKeys(Pwd);
		PageObjects.btnSubmit(driver).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Find Particular Profile
		PageObjects.btnFndPrtPrf(driver).click();
		}catch (Exception e){
			throw(e);
		}
		}
	
	//int GetRowNumber
	public static int fgetRowNo(String sProfileId) throws Exception{
		
		int iRow=0;	
	try {
		int i=0;
		String sFlag=null;
		//GetRowCount
		int iRowCount = ExcelUtils.ExcelWSheet.getLastRowNum();
		for(iRow=0;iRow<iRowCount;iRow++){
		//getCellObject
			ExcelUtils.ExcelCell = ExcelUtils.ExcelWSheet.getRow(iRow).getCell(0);
			String sXlProfileId = ExcelUtils.ExcelCell.getStringCellValue();
			if(sXlProfileId.contentEquals(sProfileId)){
				sFlag="True";
				break;
			}
			}
	}catch (Exception e){
		throw(e);
	}
	return iRow;
	}
	
		//@Test(dataProvider="ProfileIds")
		//@Test(priority=1)
		//@Parameters("sFilePath")
		public static void fGetDetails(String sFilePath) throws Exception{
			System.out.println("within Check");
			int iXlRowNo=0;
		FileOutputStream sOutFile = new FileOutputStream(sFilePath);
		ExcelUtils.ExcelWSheet= ExcelUtils.ExcelWBook.getSheet("Sheet2");
		int iRowCount = ExcelUtils.ExcelWSheet.getLastRowNum();
		for(int iRow=1;iRow<=iRowCount;iRow++){
				
				String sFindPrfId = null;
				ExcelUtils.ExcelCell = ExcelUtils.ExcelWSheet.getRow(iRow).getCell(0);
				sFindPrfId = ExcelUtils.ExcelCell.getStringCellValue();
				System.out.println("Within Calling:="+sFindPrfId);
			iXlRowNo=fgetRowNo(sFindPrfId);
			
			//Update Name & DOB
			String sActName=null;
			String sActDOB=null;
			String sActEducation=null;String sActOffice=null; String sActFatherJob=null; String sActNativePlace=null; String sActPhoneNos=null;
			PageObjects.txtMemberId(driver).clear();
			PageObjects.txtMemberId(driver).sendKeys(sFindPrfId);
			PageObjects.btnSpecificSubmit(driver).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			sActName = PageObjects.lblProfileName(driver).getText();
			sActDOB= PageObjects.lblProfileDOB(driver).getText();
			sActEducation = PageObjects.lblProfileEducation(driver).getText();
			sActOffice=PageObjects.lblProfileOffice(driver).getText();
			sActFatherJob=PageObjects.lblProfileFathersJob(driver).getText();
			sActNativePlace=PageObjects.lblProfileNativePlace(driver).getText();
			sActPhoneNos=PageObjects.lblProfilePhoneNos(driver).getText();
			fWriteCellData(iXlRowNo,1,sActName);
			fWriteCellData(iXlRowNo,2,sActDOB);		
			fWriteCellData(iXlRowNo,3,sActEducation);
			fWriteCellData(iXlRowNo,4,sActOffice);	
			fWriteCellData(iXlRowNo,5,sActFatherJob);
			fWriteCellData(iXlRowNo,6,sActNativePlace);	
			fWriteCellData(iXlRowNo,7,sActPhoneNos);	
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}
			ExcelUtils.ExcelWBook.write(sOutFile);
			sOutFile.flush();
			sOutFile.close();
		}
		//Write Cell Value
		public static void fWriteCellData(int iRowNo,int iColNo,String sActValue) throws Exception{
			ExcelUtils.ExcelWRow= ExcelUtils.ExcelWSheet.getRow(iRowNo);
			ExcelUtils.ExcelCell= ExcelUtils.ExcelWRow.getCell(iColNo, ExcelUtils.ExcelWRow.RETURN_BLANK_AS_NULL);
			if(ExcelUtils.ExcelCell==null){
				ExcelUtils.ExcelCell=ExcelUtils.ExcelWRow.createCell(iColNo);
				ExcelUtils.ExcelCell.setCellValue(sActValue);
				}
				else{
					ExcelUtils.ExcelCell.setCellValue(sActValue);
				}		
			}
}
