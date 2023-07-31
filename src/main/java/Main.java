import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebElement webElement;

        // создать веб-браузер
        WebDriver driver = new ChromeDriver();

        // перейти на сайт по адресу
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        // ввести имя Вася (метод sendKeys)
        String fieldNameLocator = "/html/body/table/tbody/tr[2]/td[2]/form/table/tbody/tr[2]/td[2]/input";
        webElement = driver.findElement(By.xpath(fieldNameLocator));
        webElement.sendKeys("Vasya");

        // ввести вес 80
        String fieldWeightLocator = "/html/body/table/tbody/tr[2]/td[2]/form/table/tbody/tr[4]/td[2]/input";
        webElement = driver.findElement(By.xpath(fieldWeightLocator));
        webElement.sendKeys("80");

        // ввести рост 180
        String fieldHeightLocator = "/html/body/table/tbody/tr[2]/td[2]/form/table/tbody/tr[3]/td[2]/input";
        webElement = driver.findElement(By.xpath(fieldHeightLocator));
        webElement.sendKeys("180");

        // выбрать пол М
        String radioButtonGenderLocator = "/html/body/table/tbody/tr[2]/td[2]/form/table/tbody/tr[5]/td[2]/input[1]";
        webElement = driver.findElement(By.xpath(radioButtonGenderLocator));
        //как нажать на найденный вэбэлемент?
        webElement.click();

        //как найти элемент по локатору (xpath)?
        String buttonCalculateLocator = "/html/body/table/tbody/tr[2]/td[2]/form/table/tbody/tr[6]/td/input";
        webElement = driver.findElement(By.xpath(buttonCalculateLocator));
        //как нажать на найденный вэбэлемент?
        webElement.click();
    }
}