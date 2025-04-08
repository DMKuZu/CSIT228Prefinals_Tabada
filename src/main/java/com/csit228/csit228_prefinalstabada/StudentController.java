package com.csit228.csit228_prefinalstabada;

import com.csit228.csit228_prefinalstabada.data.StudentView;
import com.csit228.csit228_prefinalstabada.data.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    public VBox vbStudentView;
    public TextField tfDisplayName;
    public Button btnLogout;
    public ListView<String> colQuizName;
    public ListView<Integer> colScore;
    public ListView<Button> colAnswerBtn;


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
        colScore.getItems().clear();
        colAnswerBtn.getItems().clear();

        List<Integer> score = new ArrayList<>();

        for(String name: quizNames){
            colQuizName.getItems().add(name);
            score.add(0);
            colScore.getItems().add(0);
            Button btn = new Button("Answer");
            setBtnListener(btn);
            colAnswerBtn.getItems().add(btn);
        }

        scores = new StudentView(score);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentView.txt"))){
            oos.writeObject(scores);
        } catch (IOException e) {
            System.err.println(e.getClass());
        }
    }

    public void goToLoginView() throws IOException {
        Stage stage = (Stage) vbStudentView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load(), 400, 400);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    private void setBtnListener(Button btn) {
        btn.setOnMouseClicked((MouseEvent event) ->{
            Stage stage = (Stage) vbStudentView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("answer-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load(), 600, 500);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setResizable(false);
            stage.setTitle("Answer Quiz");
            stage.setScene(scene);
            stage.show();
        });
    }
}