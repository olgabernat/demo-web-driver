import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dominos2Test {
    WebDriver driver;
    WebElement UNP;

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.get("https://www.dominospizza.pl");
    }

    @Test
    public void testOpenHomePage(){
        UNP = driver.findElement(By.xpath("//p[@class='Copy']"));
        String actual = UNP.getText();
        String expected = "© 2021 Domino's Pizza. All rights reserved.";
        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }
}
