package pPkgKMV;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGwithExcel {

	@Test(dataProvider="XLTestData", priority=1)
	public void testDP(String sUserName, String sPassword) {
		System.out.println("User Name in Calling DP is : " + sUserName);
		System.out.println("User Name in Calling DP is : " + sPassword);
	}
	
	@DataProvider(name="XLTestData")
	public Object[][] UserDetails() throws Exception{
		Object[][] ArrayCredentials =	ExcelUtils.getXlData();
		return ArrayCredentials;
	}
	}
	
	

