package opencart.project.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import opencart.project.factory.DriverFactory;
import opencart.project.pages.AccountsPage;
import opencart.project.pages.LoginPage;
import opencart.project.pages.ProductInfoPage;
import opencart.project.pages.RegistrationPage;
import opencart.project.pages.SearchPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage proInfoPage;
	protected SoftAssert softAssert; 
	protected RegistrationPage regPage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initprop();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
