package sample;

import database.DataSource;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import record.question.Question;

import javax.swing.text.TabableView;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: sample
 * Project Name: OnlineExamFacultyPortalJavaFX
 * Date: 23-04-2021
 */

public class QuestionController {
    @FXML
    private TableView<Question> questionTable;

    public QuestionController(){}

    @FXML
    public void showVivaQuestions(){
       ObservableList<Question> questions =  DataSource.showAllVivaQuestions(DatabaseConnection.getConnection());
       questionTable.setItems(questions);
    }

    @FXML
    public void showQuestions(){
        ObservableList<Question> questions = DataSource.showAllQuestionsFromQuestionBank(DatabaseConnection.getConnection());
        questionTable.setItems(questions);
    }

    public void refresh(){
        questionTable.getItems().clear();
    }
}
