package testinium_project.test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;
import testinium_project.base.BaseTest;
import testinium_project.helper.ProductPriceInfo;
import testinium_project.page.CartPage;
import testinium_project.page.HomePage;
import testinium_project.page.SearchPage;

import static org.junit.Assert.*;

public class SearchPageTest extends BaseTest {

    final static Logger logger = LogManager.getLogger(SearchPageTest.class);

    public SearchPageTest() {
        Configurator.setAllLevels(logger.getName(), Level.INFO);
    }

    @Test
    public void testSearchPageSecondPageNavigation() {
        //Arrange
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        String inputText = "gomlek";
        String secondPageUrl = "https://www.network.com.tr/search?searchKey=gomlek&page=2";
        String moreProductButtonXPath = "/html//div[@id='pagedListContainer']/div[@class='container']/div[2]/button";

        //Act
        homePage.search(inputText);
        searchPage.scrollDown(moreProductButtonXPath);
        searchPage.clickShowMoreButton(moreProductButtonXPath);

        String actualUrl = driver.getCurrentUrl();

        //Assert
        assertEquals("Url is not correct!", secondPageUrl, actualUrl);

        logger.info("testSearchPageSecondPageNavigation passed successfully");
    }

    @Test
    public void testDiscountedProductExists(){
        //Arrange
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        String inputText = "gomlek";

        //Act
        homePage.search(inputText);
        ProductPriceInfo discountedProduct = searchPage.addDiscountedProductToCart();

        //Assert
        assertNotNull("Discounted product not found", discountedProduct);

        logger.info("testDiscountedProductExists passed successfully");
    }

    @Test
    public void testSearchPageAddProductToCart() {
        //Arrange
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        CartPage cartPage = new CartPage(driver);
        String inputText = "gomlek";

        //Act
        homePage.search(inputText);
        ProductPriceInfo productPriceInfo = searchPage.addDiscountedProductToCart();
        searchPage.goToCartDetail();
        ProductPriceInfo cartPriceInfo = cartPage.getProductPriceInfo();

        //Assert
        assertEquals("Old Price is not correct!", productPriceInfo.oldPrice, cartPriceInfo.oldPrice);
        assertEquals("New Price is not correct!", productPriceInfo.newPrice, cartPriceInfo.newPrice);
        assertTrue("New price should lower than the old price", cartPriceInfo.newPrice.doubleValue() < cartPriceInfo.oldPrice.doubleValue());

        logger.info("testSearchPageAddProductToCart passed successfully");
    }

    @Test
    public void testSearchPageCartEmpty() {
        //Arrange
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        CartPage cartPage = new CartPage(driver);
        String inputText = "gomlek";

        //Act
        homePage.search(inputText);
        searchPage.addDiscountedProductToCart();
        searchPage.clickNetworkLogo();
        homePage.clickCartIcon();
        homePage.removeProductFromCart();
        homePage.clickCartIcon();

        boolean result = homePage.hasEmptyBasket();

        //Assert
        assertTrue("New price should lower than the old price", result);

        logger.info("testSearchPageCartEmpty passed successfully");
    }
}
