package pPkgKMV;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGwithExcel {

	@Test(dataProvider="XLTestData", priority=1)
	public void testDP(String sUserName, String sPassword) throws Exception {
		KMVReUsables.fLogintoNavigate(sUserName, sPassword);
		KMVReUsables.fGetDetails(Constants.sFilePath);
	}
	@DataProvider(name="XLTestData")
	public Object[][] UserDetails() throws Exception{
		Object[][] ArrayCredentials =	ExcelUtils.getXlData();
		return ArrayCredentials;
	}
	
	
	}
	
	

