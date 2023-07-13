package opencart.project.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import opencart.project.constants.AppConstants;
import opencart.project.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> metalist;
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By msg = By.cssSelector("div.alert.alert-success");
	
	private By header = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By metadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By pricing = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getProductHeaderText() {
		String text = eleUtil.getElementText(header);
		System.out.println("product header value: "+text);
		return text;
	}
	public int getProductImagesCount() {
		int size = eleUtil.waitForElementsVisible(images, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Images count: "+size);
		return size;
	}
	public Map<String, String> getMetaData() {
		metalist = new HashMap<String, String>();
		//metalist = new LinkedHashMap<String, String>();
		//metalist = new TreeMap<String, String>();
		metalist.put("productName", getProductHeaderText());
		getProductMetaData();
		getProductMetaPrice();
		return metalist;
	}
	private void getProductMetaData() {
		List<WebElement> meta = eleUtil.getElements(metadata);
		for(WebElement e:meta) {
			String text = e.getText();
			String[] metainfo = text.split(":");
			metalist.put(metainfo[0].trim(), metainfo[1].trim());
		}
	}
	private void getProductMetaPrice() {
		List<WebElement> metapri = eleUtil.getElements(pricing);
		String price = metapri.get(0).getText();
		String ext = metapri.get(1).getText();
		String exttax = ext.split(":")[1].trim();
		metalist.put("productprice", price);
		metalist.put("exTax", exttax);	
	}
	public void enterQuantity(int quan) {
		eleUtil.doSendKeys(quantity, String.valueOf(quan));
		
	}
	public String addProductToCart() {
		eleUtil.doClick(addToCart);
		String succMsg = eleUtil.waitForElementVisible(msg, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		succMsg = succMsg.substring(0, succMsg.length()-1).replace("\n", "");
		System.out.println("Succ Msg: "+succMsg);
		return succMsg;
	}
	
}
