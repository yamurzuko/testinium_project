package testinium_project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testinium_project.base.BasePage;

public class HomePage extends BasePage {

    private final By searchInput = By.id("search");

    private final By buttonCart = By.xpath("/html/body/div[2]/header/div/div/div[3]/div[3]/button");

    private final By buttonRemoveProductFromCart = By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[2]/div/div[3]");

    private final By headerEmptyBasket = By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[2]");

    private final By buttonModelRemoveProduct = By.xpath("/html/body/div[5]/div[2]/div/div[2]/button[2]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void search(String text) {
        WebElement element = findElement(searchInput);
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    public void clickCartIcon() {
        click(buttonCart);
    }

    public void removeProductFromCart() {
        click(buttonRemoveProductFromCart);
        click(buttonModelRemoveProduct);
    }

    public boolean hasEmptyBasket() {
        return findElement(headerEmptyBasket) != null;
    }
}
