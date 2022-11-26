package testinium_project.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testinium_project.base.BasePage;
import testinium_project.helper.ProductPriceInfo;

import java.util.List;

public class SearchPage extends BasePage {

    private final By spanDiscountedProducts = By.cssSelector(".product__discountPercent");
    private final By labelProductSize = By.cssSelector(".radio-box__label");

    private final By spanPriceInfo = By.cssSelector(".product__price");

    private final By buttonNavigateCartDetail = By.xpath("/html//div[@id='header__desktopBasket']//a[@href='/cart']");

    private final By networkLogo = By.cssSelector(".header__logo");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void clickShowMoreButton(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);

        //Scroll sonrasında navigation bar, butonun üstüne kayıyordu
        //Selenium click fonksiyonunu elementler üst üste geldiği için hata veriyordu
        //Javascript ile click'i tetiklemek zorunda kaldım,
        //sonrasında da yeni sayfa yüklenmesi için 2 saniye delay koymak zorunda kaldım.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductPriceInfo addDiscountedProductToCart() {
        List<WebElement> elements = findElements(spanDiscountedProducts);
        if (elements.size() == 0) {
            return null;
        }

        WebElement element = elements.get(0);
        WebElement priceSibling = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].nextElementSibling;", element);
        List<WebElement> prices = priceSibling.findElements(spanPriceInfo);
        ProductPriceInfo productPriceInfo = new ProductPriceInfo(prices.get(0).getText(),prices.get(1).getText());

        hover(element);

        WebElement parentSibling = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].parentNode.parentNode.previousElementSibling;", element);
        List<WebElement> sizeElements = parentSibling.findElements(labelProductSize);
        for (WebElement sizeElement : sizeElements) {
            if (sizeElement.getAttribute(".-disabled") == null) {
                driverWait.until(ExpectedConditions.elementToBeClickable(sizeElement));
                sizeElement.click();
                break;
            }
        }

        return productPriceInfo;
    }

    public void goToCartDetail() {
        click(buttonNavigateCartDetail);
    }

    public void clickNetworkLogo() {
        click(networkLogo);
    }
}
