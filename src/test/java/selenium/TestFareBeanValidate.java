package selenium;

import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestFareBeanValidate {
    @Test
    void testValidate() {
        // Setup WebDriver
        System.setProperty("webdriver.edge.driver", "src/test/java/selenium/driver/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        // // Get a future date from random.org
        driver.get("sito");
        WebElement dateElement = driver.findElement(By.xpath("//p[contains(@style, '2em')]"));
        String dateString = dateElement.getText();
        String fare = "10";
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Close the driver
        driver.quit();

        // Create a LessonBean and set the date

        ProfileTeacherBean profileTeacherBean = new ProfileTeacherBean();
        profileTeacherBean.setFare(fare);

        // Call validate and assert it returns true
        assertTrue(profileTeacherBean.fareValidate());
    }
}
