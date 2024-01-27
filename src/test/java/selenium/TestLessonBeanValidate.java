package selenium;

import com.example.lessonlink.view1.bean.LessonBean;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Alessandro MADDALENA 0294043
*/

/*
   This test verifies that the date of the lesson is in the future.
   The date is retrieved from random.org, so it is always a future date.
   The test is set up to be successful.
*/

class TestLessonBeanValidate {

    @Test
    void testValidate() {
        // Setup WebDriver
        System.setProperty("webdriver.edge.driver", "src/test/java/selenium/driver/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        // // Get a future date from random.org
        driver.get("https://www.random.org/calendar-dates/?num=1&start_day=27&start_month=3&start_year=2024&end_day=31&end_month=12&end_year=2024&mondays=on&tuesdays=on&wednesdays=on&thursdays=on&fridays=on&display=2&format=html&rnd=new");
        WebElement dateElement = driver.findElement(By.xpath("//p[contains(@style, '2em')]"));
        String dateString = dateElement.getText();
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Close the driver
        driver.quit();

        // Create a LessonBean and set the date
        LessonBean lessonBean = new LessonBean();
        lessonBean.setLessonDate(date);
        lessonBean.setLessonTime(LocalTime.now().plusHours(1).toString()); // Set a future time
        lessonBean.setTeacherId(1); // Set a random valid teacher ID

        // Call validate and assert it returns true
        assertTrue(lessonBean.validate());
    }
}