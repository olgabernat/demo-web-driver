import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class BurgerKingTest {
    WebDriver driver;
    WebElement signInButtonOnMainPage;
    WebElement emailInput;
    WebElement signInButtonOnEmailPage;
    WebElement acceptCookieButton;

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.get("https://www.bk.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        acceptCookieButton = driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
        acceptCookieButton.click();
        signInButtonOnMainPage = driver.findElement(By.xpath("//div[@data-testid='mobile-nav-signup-link']"));
        signInButtonOnMainPage.click();
        emailInput = driver.findElement(By.xpath("//input[@autocomplete='email']"));
        signInButtonOnEmailPage = driver.findElement(By.xpath("//div[@data-testid='signin-button']"));
    }

    @Test
    @DisplayName("Blank input")
    public void testBlankInput() {
        emailInput.sendKeys("");
        signInButtonOnEmailPage.click();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"field-react-aria-1\"]/div/div/div"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Email is a required field.";
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @DisplayName("Entering email in invalid format")
    public void invalidInput() {
        emailInput.sendKeys("Test");
        signInButtonOnEmailPage.click();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"field-react-aria-1\"]/div/div/div"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "That doesn't look like a valid email.";
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    @DisplayName("Entering email in valid format")
    public void validInput() {
        emailInput.sendKeys("test@test.com");
        signInButtonOnEmailPage.click();
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@autocomplete='name-given']"));
        firstNameInput.sendKeys("Olya");
        WebElement agreeCheckBox = driver.findElement(By.xpath("//*[@id=\"field-react-aria-3\"]/div"));
        agreeCheckBox.click();
        WebElement createAccountButton = driver.findElement(By.xpath("//*[@id=\"scroll-container\"]/div/div/div/div/div/div/div[3]/div/div/div[6]/div/div"));
        createAccountButton.click();
        WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div/div[2]/div/div/div[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "We sent an email with login instructions to test@test.com";
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }
}
