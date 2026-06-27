/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 3 - GUI for Student Class
// واجهة إدارة الطلاب

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

public class StudentForm extends Application {

    private ArrayList<Student> studentList = new ArrayList<>();

    private TextField tfId     = new TextField(); // ID
    private TextField tfName   = new TextField(); // اسم
    private TextField tfEmail  = new TextField(); // ايميل
    private TextField tfGpa    = new TextField(); // معدل
    private TextField tfPoints = new TextField(); 
    private TextArea  taOutput = new TextArea(); // عرض
    private Label     lblStatus = new Label();

    @Override
    public void start(Stage stage) {

        Label title = new Label("Student Management - SEPP");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setTextFill(Color.web("#1a5276"));

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(8);
        grid.setPadding(new Insets(10, 0, 10, 0));

        addRow(grid, "Student ID",    tfId,     0);
        addRow(grid, "Full Name *",   tfName,   1);
        addRow(grid, "Email *",       tfEmail,  2);
        addRow(grid, "GPA (0.0-4.0)", tfGpa,    3);
        addRow(grid, "Initial Points",tfPoints, 4);

        tfId.setPromptText("e.g. S-2024-001");
        tfEmail.setPromptText("e.g. student@uqu.edu.sa");
        tfGpa.setPromptText("e.g. 3.5");

        Button btnAdd   = makeButton("Add Student", "#1a5276");
        Button btnClear = makeButton("Clear",       "#922b21");
        HBox buttons = new HBox(10, btnAdd, btnClear);
        buttons.setAlignment(Pos.CENTER_LEFT);

        lblStatus.setFont(Font.font("Arial", 13));

        taOutput.setEditable(false);
        taOutput.setPrefHeight(200);
        taOutput.setFont(Font.font("Courier New", 12));

        Label lblList = new Label("Student List:");
        lblList.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        VBox root = new VBox(10, title, new Separator(),
                grid, lblStatus, buttons,
                new Separator(), lblList, taOutput);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color:#f4f6f7;");

        btnAdd.setOnAction(e -> handleAdd());
        btnClear.setOnAction(e -> handleClear());

        stage.setScene(new Scene(root, 500, 560));
        stage.setTitle("SEPP - Student Form");
        stage.show();
    }

    private void handleAdd() {
        lblStatus.setText("");
        String name  = tfName.getText().trim();
        String email = tfEmail.getText().trim();

        // شرط 1: الاسم لا يكون فارغاً
        if (name.isEmpty()) {
            setError("Error: Full Name cannot be empty."); return;
        }
        // شرط 2: البريد يحتوي على @
        if (!email.contains("@")) {
            setError("Error: Email must contain '@'."); return;
        }

        String idTxt  = tfId.getText().trim();
        String gpaTxt = tfGpa.getText().trim();
        String ptsTxt = tfPoints.getText().trim();

        double gpa = 0.0;
        if (!gpaTxt.isEmpty()) {
            try {
                gpa = Double.parseDouble(gpaTxt);
                if (gpa < 0 || gpa > 4.0) { setError("Error: GPA must be between 0.0 and 4.0."); return; }
            } catch (NumberFormatException e) {
                setError("Error: GPA must be a valid number."); return;
            }
        }

        int pts = 0;
        if (!ptsTxt.isEmpty()) {
            try { pts = Integer.parseInt(ptsTxt); } catch (NumberFormatException ignored) {}
        }

        String id = idTxt.isEmpty() ? "S-" + (studentList.size() + 1) : idTxt;
        Student s = new Student(id, name, email, gpa);
        if (pts > 0) s.addPoints(pts);
        studentList.add(s);

        taOutput.appendText("[" + studentList.size() + "] "
                + name + " | " + email
                + " | GPA: " + String.format("%.2f", gpa) + "\n");

        setSuccess("Student added! Total: " + studentList.size());
        handleClear();
    }

    private void handleClear() {
        tfId.clear(); tfName.clear(); tfEmail.clear();
        tfGpa.clear(); tfPoints.clear();
        tfId.requestFocus();
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
