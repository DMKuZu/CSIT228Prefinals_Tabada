package com.csit228.csit228_prefinalstabada;

import com.csit228.csit228_prefinalstabada.data.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class LoginController {

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

    public void onUsernameClick(){
        tfUsername.clear();
        tfUsername.setStyle("-fx-border-color : black" );
    }
    public void onPasswordClick(){
        tfPassword.clear();
        tfPassword.setStyle("-fx-border-color : black");
    }
    public void onSubmitClick() throws IOException {
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        if(db.validateUser(username,password)){
            currentUser = db.isTeacher(username,password);

            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.txt"))){
                oos.writeObject(currentUser);
                System.out.println("naay user");
            } catch (IOException e) {
                System.err.println(e.getClass());
            }

            if(currentUser.type == 1)
                goToTeacherView();
            else
                goToStudentView();
        }
        else{
            tfUsername.setText("Invalid or Incorrect username");
            tfPassword.setText("Invalid or Incorrect password");
            tfUsername.setStyle("-fx-border-color : red");
            tfPassword.setStyle("-fx-border-color : red");
        }

    }
}