package opencart.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import opencart.project.constants.AppConstants;
import opencart.project.utils.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil; 
	private By searchProdResults = By.cssSelector("div#content div.product-layout.product-grid");
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public int getSearchProductsCount() {
		int size = eleUtil.waitForElementsVisible(searchProdResults, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Products count: "+size);
		return size;
	}
	public ProductInfoPage selectTheProduct(String productName) {
		By prolocator = By.linkText(productName);
		eleUtil.waitForElementVisible(prolocator, AppConstants.DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}

}
