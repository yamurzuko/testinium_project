package testinium_project.test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;
import testinium_project.base.BaseTest;

import static org.junit.Assert.assertEquals;

public class HomePageTest extends BaseTest {

    final static Logger logger = LogManager.getLogger(HomePageTest.class);

    public HomePageTest() {
        Configurator.setAllLevels(logger.getName(), Level.INFO);
    }

    @Test
    public void testHomePageUrlCorrect() {
        //Arrange
        String expectedUrl = "https://www.network.com.tr/";

        //Act
        String actualUrl = driver.getCurrentUrl();

        //Assert
        assertEquals("Url is not correct!", expectedUrl, actualUrl);

        logger.info("testHomepageUrlCorrect passed successfully");
    }
}
