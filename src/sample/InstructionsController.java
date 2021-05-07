package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlineserver.OnlineConnectionServer;

import java.io.IOException;
import java.util.Optional;


/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: sample
 * Project Name: OnlineExamFacultyPortalJavaFX
 * Date: 22-04-2021
 */

public class InstructionsController {
    @FXML
    private Button optionOne;
    @FXML
    private Button optionTwo;
    @FXML
    private Button optionThree;
    @FXML
    private Button optionFour;
    @FXML
    private Button optionFive;
    @FXML
    private Button optionSix;
    @FXML
    private Button optionSeven;
    @FXML
    private Label headerText;
    @FXML
    private BorderPane instructionBorderPane;

    public void endExam() {
        System.exit(0);
    }

    public void showVivaQuestions() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("questionwindow.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Questions Table");
        primaryStage.initOwner(instructionBorderPane.getScene().getWindow());
        primaryStage.setScene(new Scene(root, 600, 500));
        QuestionController questionController = fxmlLoader.getController();
        questionController.showVivaQuestions();
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> questionController.refresh());
    }

    public void showAllQuestions() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("questionwindow.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Questions Table");
        primaryStage.initOwner(instructionBorderPane.getScene().getWindow());
        primaryStage.setScene(new Scene(root, 600, 500));
        QuestionController questionController = fxmlLoader.getController();
        questionController.showQuestions();
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> questionController.refresh());
    }

    public void showStudentRecord() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("studentrecord.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Students Record Table");
        primaryStage.initOwner(instructionBorderPane.getScene().getWindow());
        primaryStage.setScene(new Scene(root, 900, 800));
        StudentRecordController studentRecordController = fxmlLoader.getController();
        studentRecordController.showStudentsRecord();
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> studentRecordController.refresh());
    }

    public void addVivaQuestion() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(instructionBorderPane.getScene().getWindow());
        dialog.setTitle("Add Viva Question");
        dialog.setHeaderText("Enter Viva Question");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addquestion.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        AddQuestionController addQuestionController = fxmlLoader.getController();
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            addQuestionController.addVivaQuestion();
        }

    }

    public void addQuestion() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(instructionBorderPane.getScene().getWindow());
        dialog.setTitle("Add Question");
        dialog.setHeaderText("Enter Question");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addquestion.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        AddQuestionController addQuestionController = fxmlLoader.getController();
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            addQuestionController.addQuestion();
        }
    }

    public void startExam(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Examination Started");
        alert.setHeaderText("Please wait for student to attend the exam.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            OnlineConnectionServer.activateServer(true);
            alert.close();
        }
    }
}
