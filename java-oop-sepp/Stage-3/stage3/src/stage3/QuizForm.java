/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 3 - GUI for Quiz Class
// واجهة إدارة الاختبارات

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class QuizForm extends Application {

    private ArrayList<Quiz> quizList = new ArrayList<>(); // قائمة كويزات

    private TextField tfTitle     = new TextField(); // عنوان
    private TextField tfCourse    = new TextField(); // كورس
    private TextField tfDuration  = new TextField();  // مدة
    private TextField tfQuestions = new TextField();
    private CheckBox  cbActive    = new CheckBox("Activate immediately");  // تفعيل
    private TextArea  taOutput    = new TextArea(); // عرض
    private Label     lblStatus   = new Label();

    @Override
    public void start(Stage stage) {

        Label title = new Label("Quiz Management - SEPP");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setTextFill(Color.web("#1a5276"));

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(8);
        grid.setPadding(new Insets(10, 0, 10, 0));

        addRow(grid, "Quiz Title *",          tfTitle,     0);
        addRow(grid, "Course Name",           tfCourse,    1);
        addRow(grid, "Duration (min) *",      tfDuration,  2);
        addRow(grid, "Number of Questions *", tfQuestions, 3);

        Label lblCb = new Label("Status");
        lblCb.setMinWidth(130); lblCb.setFont(Font.font("Arial", 13));
        grid.add(lblCb, 0, 4); grid.add(cbActive, 1, 4);

        tfTitle.setPromptText("e.g. Java Basics Quiz");
        tfDuration.setPromptText("e.g. 30");
        tfQuestions.setPromptText("e.g. 10");

        Button btnAdd   = makeButton("Add Quiz", "#1a5276");
        Button btnClear = makeButton("Clear",    "#922b21");
        HBox buttons = new HBox(10, btnAdd, btnClear);
        buttons.setAlignment(Pos.CENTER_LEFT);

        lblStatus.setFont(Font.font("Arial", 13));

        taOutput.setEditable(false);
        taOutput.setPrefHeight(200);
        taOutput.setFont(Font.font("Courier New", 12));

        Label lblList = new Label("Quiz List:");
        lblList.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        VBox root = new VBox(10, title, new Separator(),
                grid, lblStatus, buttons,
                new Separator(), lblList, taOutput);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color:#f4f6f7;");

        btnAdd.setOnAction(e -> handleAdd());
        btnClear.setOnAction(e -> handleClear());

        stage.setScene(new Scene(root, 500, 560));
        stage.setTitle("SEPP - Quiz Form");
        stage.show();
    }

    private void handleAdd() {
        lblStatus.setText("");
        String titleTxt = tfTitle.getText().trim();
        String durTxt   = tfDuration.getText().trim();
        String qTxt     = tfQuestions.getText().trim();

        // شرط 1: عنوان الاختبار لا يكون فارغاً
        if (titleTxt.isEmpty()) {
            setError("Error: Quiz Title cannot be empty."); return;
        }
        // شرط 2: المدة رقم أكبر من 0
        int duration;
        try {
            duration = Integer.parseInt(durTxt);
            if (duration <= 0) { setError("Error: Duration must be greater than 0."); return; }
        } catch (NumberFormatException e) {
            setError("Error: Duration must be a valid number."); return;
        }

        int numQ = 0;
        if (!qTxt.isEmpty()) {
            try { numQ = Integer.parseInt(qTxt); } catch (NumberFormatException ignored) {}
        }

        String courseTxt = tfCourse.getText().trim();
        Quiz quiz = new Quiz(titleTxt, courseTxt.isEmpty() ? "General" : courseTxt, duration);
        if (cbActive.isSelected()) quiz.activate();
        quizList.add(quiz);

        taOutput.appendText("[" + quizList.size() + "] "
                + titleTxt + " | " + (courseTxt.isEmpty() ? "General" : courseTxt)
                + " | " + duration + " min | Q: " + numQ
                + " | Active: " + (cbActive.isSelected() ? "Yes" : "No") + "\n");

        setSuccess("Quiz added! Total: " + quizList.size());
        handleClear();
    }

    private void handleClear() {
        tfTitle.clear(); tfCourse.clear();
        tfDuration.clear(); tfQuestions.clear();
        cbActive.setSelected(false);
        tfTitle.requestFocus();
    }

    private void setError(String m)   { lblStatus.setTextFill(Color.RED);            lblStatus.setText(m); }
    private void setSuccess(String m) { lblStatus.setTextFill(Color.web("#1e8449")); lblStatus.setText(m); }

    private void addRow(GridPane g, String lbl, TextField tf, int row) {
        Label l = new Label(lbl);
        l.setMinWidth(140); l.setFont(Font.font("Arial", 13));
        tf.setPrefWidth(260);
        g.add(l, 0, row); g.add(tf, 1, row);
    }

    private Button makeButton(String text, String color) {
        Button b = new Button(text);
        b.setStyle("-fx-background-color:" + color + "; -fx-text-fill:white;"
                + "-fx-font-size:13px; -fx-padding:7 18; -fx-background-radius:5;");
        return b;
    }

    public static void main(String[] args) { launch(args); }
}


