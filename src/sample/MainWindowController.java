package sample;

import database.DataSource;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Optional;

public class MainWindowController {
    private static final MainWindowController instance = new MainWindowController();
    @FXML
    private TextField id;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private Button submit;
    @FXML
    private Button cancel;
    @FXML
    private BorderPane mainBorderPane;

    public static MainWindowController getInstance(){
        return instance;
    }

    public Optional<ButtonType> showConfirmationMessage(String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    public void showErrorMessage(String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void initialize() {
        submit.setDisable(true);
        cancel.setDisable(true);
    }

    public void handleKeyPressed() {
        if (!id.getText().isEmpty() || !id.getText().trim().isEmpty()) {
            if (!userName.getText().isEmpty() || !userName.getText().trim().isEmpty()) {
                if (!password.getText().isEmpty() || !password.getText().trim().isEmpty()) {
                    submit.setDisable(false);
                    cancel.setDisable(false);
                } else {
                    submit.setDisable(true);
                    cancel.setDisable(true);
                }
            } else {
                submit.setDisable(true);
                cancel.setDisable(true);
            }
        } else {
            submit.setDisable(true);
            cancel.setDisable(true);
        }
    }

    public void loginToExam() {
        int idOfInvigilator = Integer.parseInt(id.getText());
        String userNameOfInvigilator = userName.getText();
        String password1 = password.getText();
        if (DataSource.checkInvigilatorValidity(DatabaseConnection.getConnection(),
                idOfInvigilator, userNameOfInvigilator, password1)) {
            Optional<ButtonType> result = showConfirmationMessage("Success", "Confirmation Message",
                    "You had successfully logged in.");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                loadInstructions();
            }
        } else {
            showErrorMessage("Failure", "Error Message", "Cannot log in to portal. Kindly check your credentials");
        }
    }

    private void loadInstructions() {
        Dialog<ButtonType> dialog = showNewDialog(mainBorderPane, "Instructions", "Kindly read the " +
                        "instructions and press the corresponding button to do the same. "
                , "instructions.fxml");
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dialog.close();
        }
    }

    public Dialog<ButtonType> showNewDialog(Pane pane, String title, String headerText, String fxmlFile) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxmlFile));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        return dialog;
    }

    public void clearFields() {
        id.clear();
        userName.clear();
        password.clear();
        submit.setDisable(true);
        cancel.setDisable(true);
    }
}
