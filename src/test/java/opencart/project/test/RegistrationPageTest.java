package opencart.project.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import opencart.project.base.BaseTest;
import opencart.project.constants.AppConstants;
import opencart.project.utils.XLUtil;

public class RegistrationPageTest extends BaseTest{
	@BeforeClass
	public void RegistrationPagesetUp() {
		regPage = loginPage.navigateToRegister();
	}
	public String getRandomEmail() {
		Random random = new Random();
		String email="automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getRegTestData() {
		Object[][] regData = XLUtil.getXLData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String firstName,String lastName,String telephone,String password,String subscribe) {
		boolean flag = regPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe);
		Assert.assertTrue(flag);
	}

}