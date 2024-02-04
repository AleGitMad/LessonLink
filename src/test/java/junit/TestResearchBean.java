package junit;

import com.example.lessonlink.view1.bean.ResearchBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/* Alessandro Maddalena 0294043 */

class TestResearchBean {
    @Test
    void testValidate() {
        ResearchBean researchBean = new ResearchBean();
        researchBean.setSubject("Math");
        researchBean.setWhere("Roma");
        assertTrue(researchBean.validate());
    }
}
