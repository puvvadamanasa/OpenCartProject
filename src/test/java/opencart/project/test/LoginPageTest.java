package opencart.project.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import opencart.project.base.BaseTest;
import opencart.project.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test(priority=2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_VALUE));
	}
	@Test(priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotpwdlinkExist());
	}
	@Test(priority=4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	

}
