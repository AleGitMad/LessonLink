package com.example.lessonlink.view1;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.TeacherBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/*
public class SearchPageControllerG {
    @FXML
    private ComboBox<String> subjectBox;
    @FXML
    private ComboBox<String> whereBox;
    @FXML
    private Button searchButton;
    @FXML
    private Pane notFoundPane;
    @FXML
    private Pane errorPane;
    @FXML
    private Label errorLabel;

    @FXML
    void search() {
        ResearchBean researchBean = new ResearchBean(subjectBox.getValue());
        if (whereBox.getValue().equals("Online")) {
            researchBean.setIsOnline();
        } else {
            researchBean.setWhere(whereBox.getValue());
        }
        //no need to validate data as chosen from combobox
        TeacherBean teacherBean = new TeacherBean();
        try {
            teacherBean = ResearchController.getInstance().search(researchBean);
            if (!teacherBean.getTeachers().isEmpty()) {
                setResultsPage(teacherBean);
            } else {
                notFoundPane.setVisible(true);
            }
        } catch (FailedResearchException e) {
            errorPane.setVisible(true);
            errorLabel.setText(e.getMessage());
        }
    }

    private void setResultsPage(TeacherBean teacherBean) {
        FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
        ResultsPageControllerG resultsPageControllerG = loader.getController();
        resultsPageControllerG.setTeachers(teacherBean);
    }


}
*/