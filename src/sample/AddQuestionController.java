package sample;

import database.DataSource;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.xml.crypto.Data;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: sample
 * Project Name: OnlineExamFacultyPortalJavaFX
 * Date: 02-05-2021
 */

public class AddQuestionController {
    @FXML
    private TextField question;
    @FXML
    private Button add;

    public AddQuestionController(){}

    public void addVivaQuestion(){
        String question1 = question.getText();
        DataSource.addVivaQuestion(DatabaseConnection.getConnection(), question1);
    }

    public void addQuestion(){
        String question1 = question.getText();
        DataSource.addQuestionToQuestionBank(DatabaseConnection.getConnection(), question1);
    }
}
