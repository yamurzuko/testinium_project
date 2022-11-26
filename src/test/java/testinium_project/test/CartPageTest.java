package testinium_project.test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;
import testinium_project.base.BaseTest;
import testinium_project.page.CartPage;
import testinium_project.page.HomePage;
import testinium_project.page.LoginPage;
import testinium_project.page.SearchPage;

import static org.junit.Assert.assertTrue;

public class CartPageTest extends BaseTest {

    final static Logger logger = LogManager.getLogger(CartPageTest.class);

    public CartPageTest() {
        Configurator.setAllLevels(logger.getName(), Level.INFO);
    }

    @Test
    public void testCartPageContinueLogin() {
        //Arrange
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        CartPage cartPage = new CartPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String inputText = "gomlek";

        //Act
        homePage.search(inputText);
        searchPage.addDiscountedProductToCart();
        searchPage.goToCartDetail();
        cartPage.clickContinueButton();
        loginPage.fillLoginCredentials();

        boolean result = loginPage.hasLoginButton();

        //Assert
        assertTrue("Login button should exists", result);

        logger.info("testCartPageContinueLogin passed successfully");
    }
}
