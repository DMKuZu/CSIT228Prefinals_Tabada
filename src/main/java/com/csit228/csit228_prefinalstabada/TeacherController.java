package com.csit228.csit228_prefinalstabada;

import com.csit228.csit228_prefinalstabada.data.StudentView;
import com.csit228.csit228_prefinalstabada.data.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherController {
    public VBox vbTeacherView;
    public ListView colQuizName;
    public ListView colNumOfItems;
    public ListView colViewBtn;
    public TextField tfDisplayName;
    public Button btnLogout;

    private User currentUser;
    private StudentView scores;
    private DatabaseConnection db = new DatabaseConnection();


    public void initialize(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.txt"))){
            currentUser = (User) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getClass());
        }
        tfDisplayName.setText(currentUser.lastname+", "+currentUser.firstname);

        List<String> quizNames = db.getStudentQuizData();
        colQuizName.getItems().clear();
        colNumOfItems.getItems().clear();
        colViewBtn.getItems().clear();

        List<Integer> score = new ArrayList<>();

        /*for(String name: quizNames){
            colQuizName.getItems().add(name);
            score.add(0);
            colScore.getItems().add(0);
            Button btn = new Button("Answer");
            setBtnListener(btn);
            colAnswerBtn.getItems().add(btn);
        }*/

        scores = new StudentView(score);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentView.txt"))){
            oos.writeObject(scores);
        } catch (IOException e) {
            System.err.println(e.getClass());
        }
    }

    public void goToLoginView() throws IOException {
        Stage stage = (Stage) vbTeacherView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load(), 400, 400);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    private void setBtnListener(Button btn) {
        btn.setOnMouseClicked((MouseEvent event) ->{
            Stage stage = (Stage) vbTeacherView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load(), 600, 500);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setResizable(false);
            stage.setTitle("View Quiz");
            stage.setScene(scene);
            stage.show();
        });
    }
}