/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 3 - GUI for Question Class
// واجهة إدارة الأسئلة

// نفس فكرة CourseForm لكن للأسئلة
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

public class QuestionForm extends Application {

    private ArrayList<Question> questionList = new ArrayList<>(); // قائمة أسئلة

    private TextField tfText    = new TextField(); // نص السؤال
    private TextField tfAnswer  = new TextField(); // الإجابة
    private TextField tfOption1 = new TextField(); // خيار 1
    private TextField tfOption2 = new TextField(); // خيار 2
    private ComboBox<String> cbDifficulty = new ComboBox<>(); // مستوى
    private TextArea  taOutput  = new TextArea(); // عرض
    private Label     lblStatus = new Label(); // رسالة
  
    @Override
    public void start(Stage stage) {

        Label title = new Label("Question Management - SEPP"); // عنوان
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setTextFill(Color.web("#1a5276"));

        GridPane grid = new GridPane(); // جدول
        grid.setHgap(10); grid.setVgap(8);
        grid.setPadding(new Insets(10, 0, 10, 0));

        addRow(grid, "Question Text *",  tfText,    0); // سؤال
        addRow(grid, "Correct Answer *", tfAnswer,  1); // جواب
        addRow(grid, "Option A",         tfOption1, 2); // خيار
        addRow(grid, "Option B",         tfOption2, 3); // خيار

        cbDifficulty.getItems().addAll("Easy", "Medium", "Hard"); // مستويات
        cbDifficulty.setValue("Easy"); // افتراضي
        Label lblDiff = new Label("Difficulty *"); // عنوان
        lblDiff.setMinWidth(130); lblDiff.setFont(Font.font("Arial", 13)); 
        grid.add(lblDiff, 0, 4); grid.add(cbDifficulty, 1, 4); // اختيار

        tfText.setPromptText("e.g. What is OOP?");
        tfAnswer.setPromptText("e.g. Object Oriented Programming");

        Button btnAdd   = makeButton("Add Question", "#1a5276"); // زر إضافة
        Button btnClear = makeButton("Clear",        "#922b21");
        HBox buttons = new HBox(10, btnAdd, btnClear);
        buttons.setAlignment(Pos.CENTER_LEFT);

        lblStatus.setFont(Font.font("Arial", 13));

        taOutput.setEditable(false);
        taOutput.setPrefHeight(200);
        taOutput.setFont(Font.font("Courier New", 12));
        taOutput.setPromptText("Added questions appear here...");

        Label lblList = new Label("Question List:");
        lblList.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        VBox root = new VBox(10, title, new Separator(),
                grid, lblStatus, buttons,
                new Separator(), lblList, taOutput);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color:#f4f6f7;");

        btnAdd.setOnAction(e -> handleAdd());
        btnClear.setOnAction(e -> handleClear());

        stage.setScene(new Scene(root, 500, 560));
        stage.setTitle("SEPP - Question Form");
        stage.show();
    }

    private void handleAdd() { // إضافة سؤال
        lblStatus.setText("");
        String text   = tfText.getText().trim(); // خيار 1
        String answer = tfAnswer.getText().trim(); // خيار 2
        String diff   = cbDifficulty.getValue();

        // شرط 1: نص السؤال لا يكون فارغاً
        if (text.isEmpty()) {
            setError("Error: Question Text cannot be empty."); return;
        }
        // شرط 2: الإجابة لا تكون فارغة
        if (answer.isEmpty()) {
            setError("Error: Correct Answer cannot be empty."); return;
        }

        Question q = new Question(text, answer, diff);
        String o1 = tfOption1.getText().trim();
        String o2 = tfOption2.getText().trim();
        if (!o1.isEmpty()) q.addOption(o1);
        if (!o2.isEmpty()) q.addOption(o2);

        questionList.add(q);

        taOutput.appendText("[" + questionList.size() + "] "
                + text + " | Answer: " + answer + " | Level: " + diff + "\n");

        setSuccess("Question added! Total: " + questionList.size());
        handleClear();
    }

    private void handleClear() {
        tfText.clear(); tfAnswer.clear();
        tfOption1.clear(); tfOption2.clear();
        cbDifficulty.setValue("Easy");
        tfText.requestFocus();
    }

    private void setError(String m)   { lblStatus.setTextFill(Color.RED);            lblStatus.setText(m); }
    private void setSuccess(String m) { lblStatus.setTextFill(Color.web("#1e8449")); lblStatus.setText(m); }

    private void addRow(GridPane g, String lbl, TextField tf, int row) {
        Label l = new Label(lbl);
        l.setMinWidth(130); l.setFont(Font.font("Arial", 13));
        tf.setPrefWidth(270);
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

