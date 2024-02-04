package junit;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.LessonBean;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

/* Alessandro Maddalena 0294043 */

class TestCheckSlotAvailability {
    @Test
    void testCheckSlotAvailability() throws FailedResearchException {

        //Populate the lessonBean with the necessary data
        LessonBean lessonBean = new LessonBean();
        lessonBean.setTeacherId(1);
        lessonBean.setLessonDateTime(LocalDateTime.now()); //Set to current date and time

        BookLessonController bookLessonController = new BookLessonController();
        boolean result = bookLessonController.checkSlotAvailability(lessonBean);

        // Assert
        assertTrue(result);
    }
}
