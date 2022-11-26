package testinium_project.page;

import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testinium_project.base.BasePage;

import java.io.File;
import java.io.FileReader;

public class LoginPage extends BasePage {

    private final By inputEmail = By.id("n-input-email");

    private final By inputPassword = By.id("n-input-password");

    private final By loginButton = By.xpath("/html//div[@id='wrap']/div[@class='container mainContent']//form[@id='login']/button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillLoginCredentials() {
        String email = "";
        String password = "";

        try {
            FileReader filereader = new FileReader("./src/test/java/testinium_project/resources/user_info.csv");
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.readNext();
            String[] nextRecord = csvReader.readNext();

            email = nextRecord[0];
            password = nextRecord[1];
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        findElement(inputEmail).sendKeys(email);
        findElement(inputPassword).sendKeys(password);
    }

    public boolean hasLoginButton() {
        return findElement(loginButton) != null;
    }
}
