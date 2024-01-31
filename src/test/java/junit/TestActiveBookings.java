package junit;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.controller.BookingsController;
import com.example.lessonlink.model.*;
import com.example.lessonlink.view1.bean.BookingBean;
import com.example.lessonlink.view1.bean.LessonBean;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* This test begins with the initialization of a Student object and an Admin object. The Student object is used to set the current logged in user.
   A LessonBean object is then created and a lesson is inserted using the BookLessonController. The Admin object is used to set the current logged in user again.
   The BookingsController is used to get the active bookings and the last booking is retrieved.

   A Lesson object is created using the fillLesson method, which takes the LessonBean object as input and sets the necessary attributes.

   The test verifies that the attributes of the Lesson object in the last BookingBean object retrieved match the attributes of the Lesson object created.
   The test is set up to be successful.
 */

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
