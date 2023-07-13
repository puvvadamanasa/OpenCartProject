package opencart.project.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import opencart.projects.exceptions.FrameWorkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager om;
	public static String highlight;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		om = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight").trim();
		//String url = prop.getProperty("url").trim();
		browserName = browserName.trim().toLowerCase();
		System.out.println("Browser Name: "+browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(om.getChromeOptions());
			tldriver.set(new ChromeDriver(om.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(om.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(om.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(om.getEdgeOptions());
			tldriver.set(new EdgeDriver(om.getEdgeOptions()));
		}
		else {
			System.out.println("Please right browser");
			throw new FrameWorkException("No browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	public synchronized static WebDriver getDriver() {
		return tldriver.get();
	}
	public Properties initprop() {
		prop = new Properties();
		FileInputStream fi = null;
		//mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("Envi name: "+envName);
		try {
		if(envName == null) {
			System.out.println("No env is passed .. Running tests on qa env");
			 fi = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else {
			switch(envName.toLowerCase().trim()) {
			case "qa":
				 fi = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "stage":
				 fi = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "dev":
				 fi = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "prod":
				 fi = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			default:
				System.out.println("Wrong environment no need to run testcase");
				throw new FrameWorkException("Wrong env is passed");
			}
		}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
      return prop;	
	}
	/**
	 * Take the screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File dest = new File(path);
		try {
			FileUtil.copyFile(srcFile, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
