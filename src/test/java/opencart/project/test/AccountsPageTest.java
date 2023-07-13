package opencart.project.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import opencart.project.base.BaseTest;
import opencart.project.constants.AppConstants;


public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void doLogin() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@Test
	public void accountsPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle , AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	@Test
	public void accountsPageURLTest() {
		String acturl = accPage.getAccountsPageURL();
		Assert.assertTrue(acturl.contains(AppConstants.ACCOUNTS_PAGE_URL_VALUE));
	}
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Test
	public void accHeadersCount() {
		int actcount = accPage.getHeadersCount();
		Assert.assertEquals(actcount, AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	@Test
	public void accHeadersList() {
		List<String> actList = accPage.getHeadersValList();
		System.out.println("Expected Headers List: "+AppConstants.ACCOUNTS_PAGE_EXPE_HEADERS_LIST);
		Assert.assertEquals(actList, AppConstants.ACCOUNTS_PAGE_EXPE_HEADERS_LIST);
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"Samsung"},
			{"iMac"},
			{"Apple"},
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accPage.performSearch(searchKey);	
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);
	}
	@DataProvider
	public Object[][] searchProductData() {
		return new Object[][] {
			{"Macbook","MacBook Air"},
			{"Macbook","MacBook Pro"},
			{"Samsung","Samsung Galaxy Tab 10.1"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	@Test(dataProvider = "searchProductData")
	public void searchProductTest(String searchkey,String proName) {
		searchPage = accPage.performSearch(searchkey);
		if(searchPage.getSearchProductsCount()>0) {
			proInfoPage = searchPage.selectTheProduct(proName);
			String actText = proInfoPage.getProductHeaderText();
			Assert.assertEquals(actText, proName);
		}
		
	}
	 

}
