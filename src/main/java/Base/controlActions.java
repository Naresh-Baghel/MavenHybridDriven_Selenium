package Base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constant.constantPath;
import utility.propOprations;

public class controlActions {
	protected static WebDriver driver;
	WebDriverWait wait;
protected static propOprations propOprations;
	public static void launchBrowser() {
		String browser=System.getProperty("browser")== null? "chrome":System.getProperty("browser");
		switch (browser) {
		case "chrome":
			propOprations = new propOprations(constantPath.EVN_FILEPATH);
			System.setProperty(constantPath.CHROME_DRIVER_KEY, constantPath.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
			driver.get(propOprations.getValue("url"));
			
			break;
		case "edge":
			propOprations = new propOprations(constantPath.EVN_FILEPATH);
			System.setProperty(constantPath.EDGE_DRIVER_KEY, constantPath.EDGE_DRIVER_PATH);
			driver = new EdgeDriver();
			driver.get(propOprations.getValue("url"));
			
			
			break;

		default:
			break;
		}
		
		

	}

	public static void tearDown() {
		driver.close();
	}

	public static void takeScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyDirectory(srcFile, new File(".//Screenshot//" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		wait = new WebDriverWait(driver, 30);
		WebElement e = null;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				e = driver.findElement(By.xpath(locatorValue));

		case "CSS":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			else
				e = driver.findElement(By.cssSelector(locatorValue));

		case "ID":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				e = driver.findElement(By.id(locatorValue));

		case "LINKTEXT":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				e = driver.findElement(By.linkText(locatorValue));
		case "PARTIALLINKTEXT":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			else
				e = driver.findElement(By.partialLinkText(locatorValue));

		case "NAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			else
				e = driver.findElement(By.name(locatorValue));

		default:
			return e;

		}

	}

	public static boolean isElementDisplayed(WebElement e) {
		try {
			e.isDisplayed();
			return true;

		} catch (NoSuchElementException ne) {
			return false;
		}
	}

	public boolean isElementDisplayedWithWait(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
			e.isDisplayed();
			return true;

		} catch (NoSuchElementException ne) {
			return false;
		}
	}

	public static void waitUntilElementToBeVisible(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

}
