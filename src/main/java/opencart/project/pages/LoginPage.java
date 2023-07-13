package opencart.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import opencart.project.constants.AppConstants;
import opencart.project.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotpwdlink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getLoginPageTitle() {
		String title =eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login Page Title: "+title);
		return title;
	}
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.LOGIN_PAGE_URL_VALUE);
		System.out.println("Login Page URL: "+url);
		return url;
	}
	public boolean isForgotpwdlinkExist() {
		return eleUtil.waitForElementVisible(forgotpwdlink, AppConstants.DEFAULT_SHORT_TIMEOUT).isDisplayed();
	}
	public AccountsPage doLogin(String email,String pwd) {
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(email);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	public RegistrationPage navigateToRegister() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}

}
