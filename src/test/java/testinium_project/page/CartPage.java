package testinium_project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testinium_project.base.BasePage;
import testinium_project.helper.ProductPriceInfo;

public class CartPage extends BasePage {

    private final By labelOldPrice = By.cssSelector(".summary__body .summaryItem__value");

    private final By labelNewPrice = By.cssSelector(".summary__footer .summaryItem__value");

    private final By buttonContinue = By.xpath("/html//div[@id='cop-app']/div/div[1]//div[@class='summary']/button[@type='button']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ProductPriceInfo getProductPriceInfo() {
        return new ProductPriceInfo(getText(labelOldPrice), getText(labelNewPrice));
    }

    public void clickContinueButton() {
        click(buttonContinue);
    }
}
