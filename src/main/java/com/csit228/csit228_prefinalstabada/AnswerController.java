package com.csit228.csit228_prefinalstabada;

import com.csit228.csit228_prefinalstabada.data.User;
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

    private int numOfQuestions;
    private List<String> questions;
    private List<String> answers;
    private List<Integer> quizIdList;


    public void initialize(){

    }
}