package selenium.ide;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* Leonardo Simoni 0293067 */

public class TestDivision {
    private WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "src/test/java/selenium/Driver/msedgedriver.exe");
        driver = new EdgeDriver();
        js = (JavascriptExecutor) driver;
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void divisione3() {
        driver.get("https://www.calculator.net/");
        driver.manage().window().setSize(new Dimension(1536, 502));
        driver.findElement(By.cssSelector("div:nth-child(1) > .scinm:nth-child(1)")).click();
        driver.findElement(By.cssSelector("div:nth-child(1) > .scinm:nth-child(2)")).click();
        driver.findElement(By.cssSelector("div:nth-child(4) > .sciop:nth-child(4)")).click();
        driver.findElement(By.cssSelector("div:nth-child(3) > .scinm:nth-child(3)")).click();
        driver.findElement(By.cssSelector("div:nth-child(2) > .scinm:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".scieq:nth-child(4)")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".scieq:nth-child(4)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }

        WebElement dateElement = driver.findElement(By.xpath("//*[@id='sciOutPut']"));

        assertEquals("2.1666666667", dateElement.getText().substring(1));
    }
}
