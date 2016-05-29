package pPkgKMV;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SimpleDataProvider {
	@Test(dataProvider="HardCodedDataProvider")
	public void test(String Uid,String Pwd){
		System.out.println("UserName="+Uid);
		System.out.println("Password="+Pwd);
	}
	
	@DataProvider(name="HardCodedDataProvider")
	public Object[][] testNGDataProvider(){
		Object[][] data = new Object[3][2];
		data[0][0]="User1";
		data[0][1]="Pwd1";
		data[1][0]="User2";
		data[1][1]="Pwd2";
		data[2][0]="User3";
		data[2][1]="Pwd3";
		
		return data;
	}
	}

