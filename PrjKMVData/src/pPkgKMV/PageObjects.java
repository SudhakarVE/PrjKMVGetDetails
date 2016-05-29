package pPkgKMV;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjects {
	private static WebElement element = null;
	public static WebElement lblPhoneNos(WebDriver driver){
		return element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[7]/tbody/tr[15]/td[3]/span"));
	}
	public static WebElement lblName(WebDriver driver){
		element = driver.findElement(By.cssSelector("html body center form table.sa tbody tr td table tbody tr td span.style43 span.style46.style49"));
		return element;
	}
	public static WebElement txtUid(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[3]/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td[2]/input"));
		return element;
	}
	public static WebElement txtPwd(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@name='pwad']"));
		return element;
	}
	public static WebElement btnSubmit(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@name='Submit']"));
		return element;
	}
	public static WebElement lblProfileName(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[1]/tbody/tr[1]/td[7]/span/span"));
		return element;
	}
	public static WebElement lblProfileDOB(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[1]/tbody/tr[7]/td[3]/span/span"));
		return element;
	}
	public static WebElement lblProfileEducation(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[3]/tbody/tr[2]/td[6]/span/span"));
		return element;
	}
	public static WebElement lblProfileOffice(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[3]/span"));
		return element;
	}
	public static WebElement lblProfileFathersJob(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[3]/tbody/tr[14]/td[6]/div/span"));
		return element;
	}
	public static WebElement lblProfileNativePlace(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[5]/tbody/tr[4]/td[3]/span"));
		return element;
	}
	public static WebElement lblProfilePhoneNos(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/center/form/table[1]/tbody/tr[2]/td/table[7]/tbody/tr[15]/td[3]/span"));
		return element;
	}
	public static WebElement btnFndPrtPrf(WebDriver driver){
		//element = driver.findElement(By.xpath("//a[3]/[@src='img/findparticularprofile.png']"));
		element = driver.findElement(By.xpath("/html/body/center/form/table[2]/tbody/tr[3]/td/table/tbody/tr[8]/td/div/a[3]/img"));
		//html/body/center/form/table[2]/tbody/tr[3]/td/table/tbody/tr[8]/td/div/a[3]/img
		//[@src='img/findparticularprofile.png']
		return element;
	}
	public static WebElement txtMemberId(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@name='memberid']"));
		return element;
	}
	public static WebElement btnSpecificSubmit(WebDriver driver){
		element = driver.findElement(By.xpath("/html/body/form/center/table[2]/tbody/tr[10]/td/div/span[2]/input[2]"));
		return element;
	}
	}


