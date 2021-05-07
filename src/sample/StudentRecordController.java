package sample;

import database.DataSource;
import database.DatabaseConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import record.student.Student;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: sample
 * Project Name: OnlineExamFacultyPortalJavaFX
 * Date: 02-05-2021
 */

public class StudentRecordController {
    @FXML
    private TableView<Student> studentTable;

    public StudentRecordController(){}

    public void showStudentsRecord(){
        ObservableList<Student> students = DataSource.showStudentsRecord(DatabaseConnection.getConnection());
        studentTable.setItems(students);
    }

    public void refresh(){
        studentTable.getItems().clear();
    }
}
