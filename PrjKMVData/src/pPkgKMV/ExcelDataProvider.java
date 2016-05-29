package pPkgKMV;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExcelDataProvider {
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell ExcelCell;
	private static XSSFRow ExcelRow;
	private static XSSFWorkbook ExcelOutWBook;
	private static XSSFSheet ExcelOutWSheet;
	public static WebDriver driver = null;
	
	@BeforeClass(enabled=true)
	@Parameters({"sFilePath"})
	public static void fOpenExcelFile(String sFilePath) throws Exception{
		int iXlRowNo=0;
	try{	
	FileInputStream sFile = new FileInputStream(sFilePath);
	
	ExcelWBook = new XSSFWorkbook(sFile);
	ExcelWSheet = ExcelWBook.getSheet("Sheet1");
	//Get RowCount
	}catch (Exception e){
	 throw(e);
						}
	}
	
	@Test(priority=0)
	@Parameters({"Uid","Pwd"})
	public void fLogintoNavigate(String Uid,String Pwd) throws Exception{
	try
	{
	setup("Chrome");
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
	
	//@Test(dataProvider="ProfileIds")
	@Test(priority=1)
	@Parameters("sFilePath")
	public static void check(String sFilePath) throws Exception{
		int iXlRowNo=0;
	FileOutputStream sOutFile = new FileOutputStream(sFilePath);
	int iRowCount = ExcelWSheet.getLastRowNum();
	for(int iRow=1;iRow<=iRowCount;iRow++){
			
			String sFindPrfId = null;
			ExcelCell = ExcelWSheet.getRow(iRow).getCell(0);
			sFindPrfId = ExcelCell.getStringCellValue();
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
		ExcelWBook.write(sOutFile);
		sOutFile.flush();
		sOutFile.close();
	}

	public static int fgetRowNo(String sProfileId) throws Exception{
		int iRow=0;	
	try {
		int i=0;
		String sFlag=null;
		//GetRowCount
		int iRowCount = ExcelWSheet.getLastRowNum();
		for(iRow=0;iRow<iRowCount;iRow++){
		//getCellObject
			ExcelCell = ExcelWSheet.getRow(iRow).getCell(0);
			String sXlProfileId = ExcelCell.getStringCellValue();
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
	
	public static void fWriteCellData(int iRowNo,int iColNo,String sActValue) throws Exception{
		ExcelRow = ExcelWSheet.getRow(iRowNo);
		ExcelCell = ExcelRow.getCell(iColNo, ExcelRow.RETURN_BLANK_AS_NULL);
		if(ExcelCell==null){
			ExcelCell=ExcelRow.createCell(iColNo);
			ExcelCell.setCellValue(sActValue);
			}
			else{
				ExcelCell.setCellValue(sActValue);
			}		
		}
	

public static void setup(String browser) throws Exception{
	if(browser.equalsIgnoreCase("IE"))
	{
		System.setProperty("webdriver.ie.driver",".\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	else if(browser.equalsIgnoreCase("Chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "/TOOLSQA/Installables/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
	}
	else if(browser.equalsIgnoreCase("Firefox"))
	{
		driver = new FirefoxDriver();
	}
	else
	{
		throw new Exception("Invalid Browser");
	}
	}

}


