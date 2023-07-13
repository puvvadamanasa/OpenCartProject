package opencart.project.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import opencart.project.constants.AppConstants;
import opencart.project.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By search = By.name("search");
	private By logout = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By searchBtn = By.cssSelector("button.btn.btn-default");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccountsPageTitle() {
		String title =eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Accounts Page Title: "+title);
		return title;
	}
	public String getAccountsPageURL() {
		String url = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.ACCOUNTS_PAGE_URL_VALUE);
		System.out.println("Accounts Page Title: "+url);
		return url;
	}
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search,AppConstants.DEFAULT_SHORT_TIMEOUT).isDisplayed();
	}
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logout,AppConstants.DEFAULT_SHORT_TIMEOUT).isDisplayed();
	}
	public int getHeadersCount() {
		return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_SHORT_TIMEOUT).size();
	}
	public List<String> getHeadersValList() {
		List<String> accValList = new ArrayList<>();
		List<WebElement> accsHead = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_SHORT_TIMEOUT);
		for(WebElement e:accsHead) {
			String text = e.getText();
			accValList.add(text);
		}
		System.out.println("Account Headers List: "+accValList);
		return accValList;
	}
	public SearchPage performSearch(String searchKey) {
		if(isSearchExist()) {
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchBtn);
		return new SearchPage(driver);
		}
		else {
			System.out.println("serach is not present onm the page");
			return null;
		}
	}

}
