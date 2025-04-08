package com.csit228.csit228_prefinalstabada;

import com.csit228.csit228_prefinalstabada.data.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class AnswerController {


    public TextField tfDisplayCurrentNum;
    public Button btnSaveAndBack;
    public Text txtTotalNum;
    public Text txtQuestion;
    public Button btnPrevious;
    public Button btnNext;
    public TextField tfAnswer;
    public VBox vbAnswerView;

    private int numOfQuestions;
    private List<String> questions;
    private List<String> answers;
    private List<Integer> quizIdList;

    private DatabaseConnection db = new DatabaseConnection();

    public void initialize(){
        quizIdList = db.getQuizId();

        numOfQuestions = db.getNumOfQuestions(quizIdList.get(0));
        answers = db.getAnswers(quizIdList.get(0));

        tfDisplayCurrentNum.setText("1");
        txtQuestion.setText(questions.get(0));
    }



    @FXML
    private void goToStudentView() throws IOException {
        Stage stage = (Stage) vbAnswerView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 500);
        stage.setResizable(false);
        stage.setTitle("StudentView");
        stage.setScene(scene);
        stage.show();
    }
}