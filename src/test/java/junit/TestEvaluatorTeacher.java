package junit;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.model.Admin;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
    This class tests the addTeacher method in the AddTeacherController class.
*/

/* Leonardo Simoni 0293067 */

class TestEvaluatorTeacher {
    @org.junit.jupiter.api.Test
    void testAddTeacher() {
        Admin admin = new Admin();
        admin.setUserId(2); // The user 2 have to be an admin
        LoggedUser.getInstance().setAdmin(admin);
        ProfileTeacherBean profileTeacherBean = new ProfileTeacherBean();
        profileTeacherBean.setSubject1("Math");
        profileTeacherBean.setSubject2("Science");
        profileTeacherBean.setSubject3("English");
        profileTeacherBean.setQualification("Bachelor");
        profileTeacherBean.setCity("Rome");
        profileTeacherBean.setOnline("Yes");

        AddTeacherController addTeacherController = new AddTeacherController();

        profileTeacherBean.setDecorations();
        addTeacherController.addTeacher(profileTeacherBean);

        int expectedFare = 23;
        assertEquals(Integer.parseInt(profileTeacherBean.fare()), expectedFare);
    }
}

