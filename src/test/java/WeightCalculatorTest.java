import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WeightCalculatorTest {
    WebDriver driver;
    WebElement nameInput;
    WebElement weightInput;
    WebElement heightInput;
    WebElement genderRadio;
    WebElement calculationButton;

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        weightInput = driver.findElement(By.xpath("//input[@name='weight']"));
        heightInput = driver.findElement(By.xpath("//input[@name='height']"));
        genderRadio = driver.findElement(By.xpath("//input[@value='m']"));
        calculationButton = driver.findElement(By.xpath("//input[@type='submit']"));
    }

    @Disabled
    @Test
    public void testMethod1() {
        nameInput.sendKeys("Vasya");
        weightInput.sendKeys("80");
        heightInput.sendKeys("180");
        genderRadio.click();
        calculationButton.click();
    }

    @Test
    public void testMethod2() {
        nameInput.sendKeys("Nina");
        weightInput.sendKeys("60");
        heightInput.sendKeys("150");
        genderRadio.click();
        calculationButton.click();

        Assertions.fail();
    }

    @Test
    public void testMethod3() {
        nameInput.sendKeys("");
        weightInput.sendKeys("");
        heightInput.sendKeys("");
        genderRadio.click();
        calculationButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/form/table/tbody/tr[1]/td/b"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErroMessage = "Не указано имя.\n" +
                "Рост должен быть в диапазоне 50-300 см.\n" +
                "Вес должен быть в диапазоне 3-500 кг.";
        Assertions.assertEquals(expectedErroMessage, actualErrorMessage);
    }

    @Test
    public void testMethod4() {
        nameInput.sendKeys("Olga");
        weightInput.sendKeys("");
        heightInput.sendKeys("");
        genderRadio.click();
        calculationButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/form/table/tbody/tr[1]/td/b"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErroMessage = "Рост должен быть в диапазоне 50-300 см.\n" +
                "Вес должен быть в диапазоне 3-500 кг.";
        Assertions.assertEquals(expectedErroMessage, actualErrorMessage);
    }

    @Test
    @DisplayName("Это название")
    public void testMethod5() {
        nameInput.sendKeys("Nina");
        weightInput.sendKeys("301");
        heightInput.sendKeys("100");
        genderRadio.click();
        calculationButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErroMessage = "Значительный избыток массы тела, тучность";
        Assertions.assertEquals(expectedErroMessage, actualErrorMessage);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }
}

