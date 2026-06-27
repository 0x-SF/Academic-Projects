/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 3 - GUI for Course Class
// واجهة إدارة الدورات التعليمية

import javafx.application.Application; // تشغيل JavaFX
import javafx.geometry.Insets; // padding داخل الواجهة
import javafx.geometry.Pos; // محاذاة العناصر
import javafx.scene.Scene; // الشاشة
import javafx.scene.control.*; // كل عناصر الواجهة
import javafx.scene.layout.*; // ترتيب العناصر
import javafx.scene.paint.Color; // الألوان
import javafx.scene.text.*; // تنسيق الخط
import javafx.stage.Stage; // النافذة
import java.util.ArrayList; // قائمة ديناميكية

public class CourseForm extends Application { // واجهة إدارة الكورسات

    private ArrayList<Course> courseList = new ArrayList<>(); // نخزن كل الكورسات
 
    private TextField tfName        = new TextField(); // اسم الكورس
    private TextField tfDescription = new TextField(); // وصف الكورس
    private TextField tfInstructor  = new TextField(); // وصف الكورس
    private TextField tfMaxStudents = new TextField(); // الحد الأقصى
    private TextField tfCategory    = new TextField(); // التصنيف
    private TextArea  taOutput      = new TextArea(); // عرض النتائج
    private Label     lblStatus     = new Label(); // رسالة الحالة

    @Override
    public void start(Stage stage) { // بداية تشغيل الواجهة

        Label title = new Label("Course Management - SEPP"); // عنوان الصفحة
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // خط عريض
        title.setTextFill(Color.web("#1a5276")); // لون العنوان

        GridPane grid = new GridPane(); // جدول ترتيب
        grid.setHgap(10); grid.setVgap(8); // مسافات بين العناصر
        grid.setPadding(new Insets(10, 0, 10, 0)); // padding

        addRow(grid, "Course Name *",   tfName,        0); // صف الاسم
        addRow(grid, "Description",     tfDescription, 1); // صف الوصف
        addRow(grid, "Instructor Name", tfInstructor,  2); // صف الدكتور
        addRow(grid, "Max Students *",  tfMaxStudents, 3); // صف العدد
        addRow(grid, "Category",        tfCategory,    4);  // صف التصنيف

        tfName.setPromptText("e.g. Java Programming"); 
        tfMaxStudents.setPromptText("e.g. 30");

        Button btnAdd   = makeButton("Add Course", "#1a5276"); // زر إضافة
        Button btnClear = makeButton("Clear",      "#922b21"); // زر مسح
        HBox buttons = new HBox(10, btnAdd, btnClear); // تجميع الأزرار
        buttons.setAlignment(Pos.CENTER_LEFT); // محاذاة

        lblStatus.setFont(Font.font("Arial", 13)); // خط الرسالة

        taOutput.setEditable(false); // منع التعديل
        taOutput.setPrefHeight(200); // ارتفاع
        taOutput.setFont(Font.font("Courier New", 12)); // خط ثابت
        taOutput.setPromptText("Added courses appear here...");

        Label lblList = new Label("Course List:");
        lblList.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        VBox root = new VBox(10, title, new Separator(),
                grid, lblStatus, buttons,
                new Separator(), lblList, taOutput); // ترتيب كامل
        root.setPadding(new Insets(20)); // padding
        root.setStyle("-fx-background-color:#f4f6f7;"); // خلفية

        btnAdd.setOnAction(e -> handleAdd()); // حدث إضافة
        btnClear.setOnAction(e -> handleClear()); // حدث مسح

        stage.setScene(new Scene(root, 500, 560)); // حجم النافذة
        stage.setTitle("SEPP - Course Form"); // عنوان
        stage.show(); // عنوان
    }

    private void handleAdd() { // إضافة كورس جديد
        lblStatus.setText("");
        String name   = tfName.getText().trim(); // قراءة الاسم
        String maxTxt = tfMaxStudents.getText().trim(); // قراءة العدد

        // شرط 1: اسم الكورس لا يكون فارغاً
        if (name.isEmpty()) {
            setError("Error: Course Name cannot be empty.");
            return; // شرط الاسم
        }

        // شرط 2: Max Students رقم أكبر من 0
        int max; // متغير العدد
        try {
            max = Integer.parseInt(maxTxt); // تحويل رقم
            if (max <= 0) { setError("Error: Max Students must be greater than 0."); return; } // شرط العدد
        } catch (NumberFormatException e) {
            setError("Error: Max Students must be a valid number.");
            return; // خطأ إدخال
        }

        String desc       = tfDescription.getText().trim();
        String instructor = tfInstructor.getText().trim();
        String category   = tfCategory.getText().trim();

        Course c = new Course(name, desc,
                instructor.isEmpty() ? "TBA" : instructor, max); // إنشاء كورس
        courseList.add(c); // إضافة للقائمة

        taOutput.appendText("[" + courseList.size() + "] "
                + name + " | " + (instructor.isEmpty() ? "TBA" : instructor)
                + " | Max: " + max
                + " | " + (category.isEmpty() ? "General" : category) + "\n"); // عرض

        setSuccess("Course added successfully! Total: " + courseList.size()); // رسالة نجاح
        handleClear();  // تنظيف
    }

    private void handleClear() { // تنظيف الحقول
        tfName.clear(); tfDescription.clear();
        tfInstructor.clear(); tfMaxStudents.clear();
        tfCategory.clear(); tfName.requestFocus();
    }

    private void setError(String m)   { lblStatus.setTextFill(Color.RED);            lblStatus.setText(m); } // خطأ
    private void setSuccess(String m) { lblStatus.setTextFill(Color.web("#1e8449")); lblStatus.setText(m); } // نجاح

    private void addRow(GridPane g, String lbl, TextField tf, int row) { // إضافة صف
        Label l = new Label(lbl);  // عنوان
        l.setMinWidth(130); l.setFont(Font.font("Arial", 13)); 
        tf.setPrefWidth(270); 
        g.add(l, 0, row);   // إضافة
        g.add(tf, 1, row); // إدخال
    }

    private Button makeButton(String text, String color) { // زر جاهز
        Button b = new Button(text); // إنشاء زر
        b.setStyle("-fx-background-color:" + color + "; -fx-text-fill:white;"
                + "-fx-font-size:13px; -fx-padding:7 18; -fx-background-radius:5;"); // لون
        return b; // رجوع
    }

    public static void main(String[] args) { launch(args); } // تشغيل
}



