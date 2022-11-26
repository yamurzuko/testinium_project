package testinium_project.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://www.network.com.tr/");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
