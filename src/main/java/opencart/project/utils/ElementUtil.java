package opencart.project.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import opencart.project.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtils jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil = new JavaScriptUtils(driver);
	}
	
	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
		 jsUtil.flash(ele);
		}
		 return ele;
	}
	public WebElement getElement(By locator,int timeout) {
		return waitForElementVisible(locator, timeout);
	}
	
	public void doSendKeys(By locator,String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	public String getElementText(By locator) {
		return getElement(locator).getText();
	}
	public boolean doElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	public String getElementAttribute(By locator,String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public void getElementAttributes(By locator,String attrName) {
		List<WebElement> eleList = getElements(locator);
		for(WebElement e:eleList){
			String attrValue = e.getAttribute(attrName);
			System.out.println(attrValue);
		}
	}
	public int getTotalElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("Total elemnts: "+eleCount);
		return eleCount;
	}
	public List<String> getElementsTextList(By locator) {
		List<String> eleList = new ArrayList<>();
		List<WebElement> list = getElements(locator);
		for(WebElement e:list) {
			String text = e.getText();
			eleList.add(text);	
		}
		return eleList;
	}
	//*************select drop down**********************//
	public void doSelectDropDownByIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	public void doSelectDropDownByValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	public void doSelectDropDownByVisibleText(By locator,String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	public  List<WebElement> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions();	
	}
	public  int getTotalDropDownOptins(By locator) {
		return getDropDownOptionsList(locator).size();
	}
	public  List<String> getDropDownOptionsTextList(By locator) {
		List<String> l = new ArrayList<>();
		List<WebElement> list = getDropDownOptionsList(locator);
		for(WebElement e:list) {
			String text = e.getText();
			l.add(text);
		}
			return l;	
	}
	public  void selectDropDownValue(By locator,String value) {
		List<WebElement> list = getDropDownOptionsList(locator);
		for(WebElement e:list) {
			String text = e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}	
		}	
	}
	public void doSearch(By locator,String value) {
		List<WebElement> l = getElements(locator);
		System.out.println(l.size()-1);
		for(WebElement e:l) {
			String text = e.getText();
			if(text.contains(value)) {
				e.click();
				break;
			}
			
		}
		
	}
	public void doActionSendKeys(By locator,String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator),value).build().perform();
	}
	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	
	public WebElement waitForElementPresence(By locator,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 
	}
	public WebElement waitForElementVisible(By locator,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 
	}
	public Alert waitForAlertPresence(int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 return wait.until(ExpectedConditions.alertIsPresent());
	}
	public String getAlertText(int timeout) {
		Alert alert = 	waitForAlertPresence(timeout);
		return alert.getText();
	}
	public void acceptAlert(int timeout) {
		Alert alert =waitForAlertPresence(timeout);
		alert.accept();
	}
	public void dismissAlert(int timeout) {
		Alert alert =waitForAlertPresence(timeout);
		alert.dismiss();
	}
	public void alertSendKeys(int timeout,String value) {
		Alert alert =waitForAlertPresence(timeout);
		alert.sendKeys(value);
	}
	public String waitForTitleContainsandFetch(int timeout,String titleFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.titleContains(titleFractionValue));
		 return driver.getTitle();
	}
	public String waitForTitleIsandFetch(int timeout,String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.titleIs(titleValue));
		 return driver.getTitle();
	}
	public String waitForURLContainsandFetch(int timeout,String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.urlContains(urlFractionValue));
		 return driver.getCurrentUrl();
	}
	public boolean waitForURLContains(int timeout,String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.urlContains(urlFractionValue));
		  
	}
	public String waitForURLIsandFetch(int timeout,String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.urlToBe(titleValue));
		 return driver.getTitle();
	}
	/**
	 * An expectation for checking that there is at least one element present on a web page
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		 
	}
	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, 
	 * isvisible. Visibility means that the element is not only displayed but also has a height 
	 * andwidth that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		 
	}
	public void waitForFrameAndSwitchToIt(String idorName,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorName));
	}
	public void waitForFrameAndSwitchToItByIndex(int frameIndex,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	public void waitForFrameAndSwitchToItByFrameElement(WebElement frameElement,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	public void waitForFrameAndSwitchToItByFrameLocator(By locator,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	public void clickWhenReady(By locator,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public WebElement waitForElementToBeClickable(By locator,int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void doClickWithActionsAndWait(By locator,int timeout) {
		WebElement ele = waitForElementToBeClickable(locator,timeout);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}



}
