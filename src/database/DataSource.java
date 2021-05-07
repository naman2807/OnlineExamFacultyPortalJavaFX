package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import record.password.Password;
import record.question.Question;
import record.student.Student;
import record.username.UserName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: OnlineAssessmentFacultyPortal
 * Date: 29-03-2021
 */

public final class DataSource {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ObservableList<Question> questions = FXCollections.observableArrayList();
    private static final ObservableList<Student> students = FXCollections.observableArrayList();

    private DataSource() {
    }

    /**
     * This method checks whether invigilator exists in database or not.
     *
     * @param connection
     * @param id
     * @param userName1
     * @param password1
     * @return true/false
     */
    public static boolean checkInvigilatorValidity(Connection connection, int id, String userName1, String password1) {
        try {
            String userName = null;
            String password = null;
            PreparedStatement preparedStatement = connection.prepareStatement(SQlQueries.findFacultyQuery());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return false;
            } else {
                while (resultSet.next()) {
                    userName = resultSet.getString(3);
                    password = resultSet.getString(4);
                }
                if (userName == null || !userName.equals(userName1) || !password.equals(password1)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * This method add question to question bank.
     *
     * @param connection
     */
    public static void addQuestionToQuestionBank(Connection connection, String question) {
        instructionToAddQuestion(connection, SQlQueries.addNewQuestionQuery(), question);
    }

    /**
     * This method add viva questions to database.
     *
     * @param connection
     */
    public static void addVivaQuestion(Connection connection, String question) {
        instructionToAddQuestion(connection, SQlQueries.addNewVivaQuestionQuery(), question);
    }

    /**
     * This method shows instructions to add questions.
     *
     * @param connection
     * @param query
     */
    private static void instructionToAddQuestion(Connection connection, String query, String question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, question);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding question");
            e.printStackTrace();
        }
    }

    /**
     * This method shows record of all students.
     *
     * @param connection
     */
    public static ObservableList<Student> showStudentsRecord(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQlQueries.showStudentRecordQuery());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int classRoll = resultSet.getInt(2);
                int uniRoll = resultSet.getInt(3);
                String section = resultSet.getString(4);
                int year = resultSet.getInt(5);
                String course = resultSet.getString(6);
                String userName = resultSet.getString(7);
                String password = resultSet.getString(8);
                Student student = new Student(name, classRoll, uniRoll, section.charAt(0), year, course,
                        new UserName(userName), new Password(password));
                students.add(student);

            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * This method shows all questions from question bank.
     *
     * @param connection
     */
    public static ObservableList<Question> showAllQuestionsFromQuestionBank(Connection connection) {
       return printQuestions(connection, SQlQueries.showAllQuestionFromQuestionBankQuery());
    }

    /**
     * This method shows all viva questions from database.
     *
     * @param connection
     */
    public static ObservableList<Question> showAllVivaQuestions(Connection connection) {
        return printQuestions(connection, SQlQueries.showAllVivaQuestionQuery());
    }

    /**
     * This method is used to print all questions from database.
     *
     * @param connection
     * @param query
     */
    private static ObservableList<Question> printQuestions(Connection connection, String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int number = resultSet.getInt(1);
                String question = resultSet.getString(2);
                Question question1 = new Question(String.valueOf(number), question);
                questions.add(question1);
            }
            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static ObservableList<Question> getQuestions() {
        return questions;
    }
}
