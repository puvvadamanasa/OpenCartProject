package opencart.project.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import opencart.project.base.BaseTest;

public class ProductInfoTest extends BaseTest{
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@DataProvider
	public Object[][] getImagesData() {
		return new Object[][] {
			{"Macbook","MacBook Air",4},
			{"Macbook","MacBook Pro",4},
			{"Samsung","Samsung Galaxy Tab 10.1",7},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6}
		};
	}
	@Test(dataProvider = "getImagesData")
	public void productImagesCountTest(String searkey,String proName,int imagescount) {
		searchPage = accPage.performSearch(searkey);
		proInfoPage = searchPage.selectTheProduct(proName);
		int actImagesCount = proInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagescount);
	}
	@DataProvider
	public Object[][] productInfo() {
		return new Object[][] {
			{"Macbook","MacBook Pro","Brand","Apple"},
			{"Macbook","MacBook Pro","Product Code","Product 18"},
			{"Macbook","MacBook Pro","productName","MacBook Pro"},
			{"Macbook","MacBook Pro","exTax","$2,000.00"},
		};
	}
	@Test(dataProvider = "productInfo")
	public void getProductInfoTest(String searkey,String proName,String key,String value) {
		searchPage = accPage.performSearch(searkey);
		proInfoPage = searchPage.selectTheProduct(proName);
		Map<String, String>  actProInfoMap = proInfoPage.getMetaData();
		System.out.println("Actual Map: "+actProInfoMap);
		softAssert.assertEquals(actProInfoMap.get(key), value);
		softAssert.assertEquals(actProInfoMap.get(key), value);
		softAssert.assertEquals(actProInfoMap.get(key), value);
		softAssert.assertEquals(actProInfoMap.get(key), value);
		softAssert.assertAll();	
	}
	@DataProvider
	public Object[][] addToCartData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",2},
			{"Macbook","MacBook Air",3},
			{"iMac","iMac",2}	
		};	
	}
	@Test(dataProvider = "addToCartData")
	public void addToCartTest(String searkey,String proName,int quanti) {
		searchPage = accPage.performSearch(searkey);
		proInfoPage = searchPage.selectTheProduct(proName);
		proInfoPage.enterQuantity(quanti);
		String actMsg = proInfoPage.addProductToCart();
		softAssert.assertTrue(actMsg.indexOf("Success")>=0);
		softAssert.assertTrue(actMsg.indexOf(proName)>=0);
		softAssert.assertEquals(actMsg, "Success: You have added "+proName+" to your shopping cart!");
		softAssert.assertAll();
	}
}
