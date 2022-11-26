package testinium_project.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    protected WebElement findElement(By by) {
        driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElement(by);
    }

    protected List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    protected WebElement findElementByCss(String css) {
        return findElement(By.cssSelector(css));
    }

    protected String getText(By by) {
        return findElement(by).getText();
    }

    protected void click(By by) {
        driverWait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    protected void hover(By by) {
        WebElement element = findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    protected void hover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void scrollDown(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
    }
}
