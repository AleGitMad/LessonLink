package junit;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
/* This test begins with the initialization of a ProfileTeacherBean object which is then transformed into a Teacher object.
   By doing so, given that the AddTeacherController method "addTeacher" takes a ProfileTeacherBean object in input, it is
   able to perform the decoration knowing only what is strictly necessary about the original ProfileTeacherBean object. The other
   attributes of the ProfileTeacherBean object are not considered in the decoration, so they are useless to the AddTeacherController.

   The "addTeacher" method decorates and returns the fare of the Teacher object. The test only verifies that the "decorated"
   fare coincides with the expected one. The test is set up to be successful.
 */



class TestEvaluatorTeacher {
    @org.junit.jupiter.api.Test
    void testAddTeacher() {
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

