package com.example.lessonlink.view1;

import com.example.lessonlink.view1.bean.ResearchBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SearchPageControllerG {
    @FXML
    private ComboBox<String> subjectBox;
    @FXML
    private ComboBox<String> whereBox;
    @FXML
    private Button searchButton;

    @FXML
    void search() {
        ResearchBean researchBean = new ResearchBean(subjectBox.getValue(), whereBox.getValue());
        //no need to validate data as chosen from combobox




    }


}
