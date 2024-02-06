package junit;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.controller.BookingsController;
import com.example.lessonlink.model.*;
import com.example.lessonlink.view1.bean.BookingBean;
import com.example.lessonlink.view1.bean.LessonBean;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/*
    This class tests the getActiveBookings method in the BookingsController class.
*/

/* Leonardo Simoni 0293067 */

class TestActiveBookings {
    @org.junit.jupiter.api.Test
    void existingData() throws Exception {
        Student student = new Student();
        student.setUserId(1); // The user 1 have to be a student
        LoggedUser.getInstance().setStudent(student);

        LocalDateTime date = LocalDateTime.now();
        BookLessonController bookLessonController = new BookLessonController();
        LessonBean lessonBean = new LessonBean();
        lessonBean.setTeacherId(1);
        lessonBean.setLessonDateTime(date);
        lessonBean.setIsOnline(true);
        lessonBean.setIsPaid(false);
        bookLessonController.insertLesson(lessonBean);

        Admin admin = new Admin();
        admin.setUserId(2); // The user 2 have to be an admin
        LoggedUser.getInstance().setAdmin(admin);
        BookingsController bookingsController = new BookingsController();
        List<BookingBean> result = bookingsController.getActiveBookings();
        BookingBean bookingBean = result.getLast();

        Lesson lesson = fillLesson(lessonBean);

        assertEquals(bookingBean.getTeacherId(), lesson.getTeacherId());
        assertEquals(bookingBean.getStudentId(), lesson.getStudentId());
        assertEquals(bookingBean.getDateTime(), convertDate(lesson.getDateTime()));
        assertEquals(bookingBean.getConfirmed(), lesson.getIsConfirmed());

    }

    private Lesson fillLesson(LessonBean lessonBean) {
        Lesson lesson = new Lesson();
        lesson.setDateTime(lessonBean.getLessonDateTime());
        lesson.setIsOnline(lessonBean.getIsOnline());
        lesson.setTeacherId(lessonBean.getTeacherId());
        lesson.setStudentId(LoggedUser.getInstance().getStudent().getUserId());
        lesson.setIsConfirmed(false);
        lesson.setIsPaid(lessonBean.getIsPaid());
        return lesson;
    }
    private String convertDate(LocalDateTime dateTime){
        String y = dateTime.toString().substring(0, 4);
        String m = dateTime.toString().substring(5, 7);
        String d = dateTime.toString().substring(8, 10);
        String h = dateTime.toString().substring(11, 13);
        String i = dateTime.toString().substring(14, 16);

        return d + "/" + m + "/" + y + " " + h + ":" + i;
    }
}
