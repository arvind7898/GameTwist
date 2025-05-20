package app.Util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.Factory.DriverFactory;

public class ElementUtil {
	
    WebDriver driver;
    public ElementUtil(WebDriver driver) {
        this.driver = DriverFactory.getDriver();
    }
    
    // ----------------- BASIC ACTIONS -----------------
    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public void doSendKeys(By locator, String value) {
        WebElement ele = getElement(locator);
        ele.clear();
        ele.sendKeys(value);
    }

    public String doGetText(By locator) {
        return getElement(locator).getText();
    }

    public String doGetAttribute(By locator, String attrName) {
        return getElement(locator).getAttribute(attrName);
    }

    public boolean doIsDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }
    
	public void selectdropDownOptionByIndex(WebElement webElement, int index) {
		//WebElement dropdownElement = driver.findElement(By webElement);
		Select select = new Select(webElement);
		select.selectByIndex(index);
	}
	public void scrollToTheElement(By locator) {
		// Use JavaScriptExecutor to scroll to the element
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
    
    
    // ----------------- WAIT METHODS -----------------

    public WebElement waitForElementToBeVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public Boolean waitForElementToBeDisappear(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForURLToContain(String urlFraction, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.urlContains(urlFraction));
    }

    public boolean waitForTitleToBe(String title, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.titleIs(title));
    }

    public boolean waitForAlertToBePresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.alertIsPresent()) != null;
    }

}
