package selenium.ide;

/* Alessandro Maddalena 0294043 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDivisionByZero {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "src/test/java/selenium/driver/msedgedriver.exe");
        driver = new EdgeDriver();
        js = (JavascriptExecutor) driver;
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testDivisionByZero() {
        // Test name: DivisionByZero
        // Step # | name | target | value
        // 1 | open | https://www.calculator.net/ |
        driver.get("https://www.calculator.net/");
        // 2 | setWindowSize | 1361x747 |
        driver.manage().window().setSize(new Dimension(1361, 747));
        // 3 | click | css=div:nth-child(1) > .scinm:nth-child(3) |
        driver.findElement(By.cssSelector("div:nth-child(1) > .scinm:nth-child(3)")).click();
        // 4 | click | css=tr:nth-child(2) > td:nth-child(2) > div |
        driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2) > div")).click();
        // 5 | click | id=sciOutPut |
        driver.findElement(By.id("sciOutPut")).click();
        // 6 | click | css=div:nth-child(4) > .sciop:nth-child(4) |
        driver.findElement(By.cssSelector("div:nth-child(4) > .sciop:nth-child(4)")).click();
        // 7 | click | css=div:nth-child(4) > .scinm:nth-child(1) |
        driver.findElement(By.cssSelector("div:nth-child(4) > .scinm:nth-child(1)")).click();
        // 8 | click | css=#homecaldiv > #contentout > table > tbody > tr > td:nth-child(1) |
        driver.findElement(By.cssSelector("#homecaldiv > #contentout > table > tbody > tr > td:nth-child(1)")).click();
        // 9 | click | id=sciOutPut |
        driver.findElement(By.id("sciOutPut")).click();
        // 10 | click | id=sciOutPut |
        driver.findElement(By.id("sciOutPut")).click();

        assertEquals("Error", driver.findElement(By.id("sciOutPut")).getText().substring(1));
    }
}
