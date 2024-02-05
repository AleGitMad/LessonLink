package junit;

import com.example.lessonlink.controller.LoginController;
import com.example.lessonlink.exceptions.FailedFileAccessException;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.LoginBean;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/* Alessandro Maddalena 0294043 */

/*
    This class tests the login method in the LoginController class.
*/

class TestLoginController {

    @Test
    void testLogin() {
        LoginController loginController = new LoginController();
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail("alessandro@gmail.com");
        loginBean.setPassword("password");
        try {
            AccountBean accountBean = loginController.login(loginBean);
            assertEquals("Student", accountBean.getRole());
            assertEquals("alessandro", accountBean.getName());
        } catch (FailedLoginException | SQLException | FailedFileAccessException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
