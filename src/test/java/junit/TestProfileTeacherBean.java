package junit;


import com.example.lessonlink.view1.bean.ProfileTeacherBean;

import static org.junit.jupiter.api.Assertions.assertTrue;

/* Leonardo Simoni 0293067 */

class TestProfileTeacherBean {
    @org.junit.jupiter.api.Test
    void testBean(){
        ProfileTeacherBean profileTeacherBean = new ProfileTeacherBean();
        profileTeacherBean.setName("Mario");
        profileTeacherBean.setCity("Roma");
        profileTeacherBean.setSubject1("Math");
        profileTeacherBean.setQualification("Master");
        profileTeacherBean.setOnline("Yes");

        assertTrue(profileTeacherBean.validate());
    }
}
