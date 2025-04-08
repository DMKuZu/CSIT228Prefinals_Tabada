package com.csit228.csit228_prefinalstabada;

import com.csit228.csit228_prefinalstabada.data.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ViewController {

    public TextField tfUsername;
    public TextField tfPassword;
    public Button btnSubmit;
    public VBox vbLoginView;

    private User currentUser;

    private DatabaseConnection db = new DatabaseConnection();

    private void goToStudentView() throws IOException {
        Stage stage = (Stage) vbLoginView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 500);
        stage.setResizable(false);
        stage.setTitle("StudentView");
        stage.setScene(scene);
        stage.show();
    }

    private void goToTeacherView() throws IOException {
        Stage stage = (Stage) vbLoginView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 500);
        stage.setResizable(false);
        stage.setTitle("Teacher View");
        stage.setScene(scene);
        stage.show();
    }

}