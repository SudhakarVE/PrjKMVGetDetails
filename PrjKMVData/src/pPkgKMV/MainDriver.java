package pPkgKMV;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class MainDriver {
	public static WebDriver driver = null;
	public static void main(String[] args) throws Exception {
		setup("Chrome");
		driver.get("http://www.adilife.com/memlogin.asp");
		//Login
		PageObjects.txtUid(driver).sendKeys("KMV20694");
		PageObjects.txtPwd(driver).sendKeys("VH435363");
		PageObjects.btnSubmit(driver).click();
		
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
