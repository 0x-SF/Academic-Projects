/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stage3;

// SEPP Project - Stage 3 - Main Entry Point
// يفتح الواجهات الأربعة دفعة واحدة

import javafx.application.Application; // نستورد JavaFX الأساسي عشان نقدر نشغل التطبيق
import javafx.geometry.Insets; // للتحكم بالمسافات الداخلية (padding)
import javafx.geometry.Pos; // عشان نحدد مكان العناصر (يمين/يسار/وسط)
import javafx.scene.Scene; // يمثل شاشة الواجهة نفسها
import javafx.scene.control.Button; // زر في الواجهة
import javafx.scene.control.Label; // نص ثابت في الواجهة
import javafx.scene.layout.VBox; // ترتيب العناصر بشكل عمودي
import javafx.scene.paint.Color; // عشان الألوان
import javafx.scene.text.*; // تنسيق الخطوط (حجم، سمك، ...)
import javafx.stage.Stage; // النافذة الرئيسية

public class Stage3 extends Application { // الكلاس الرئيسي للتطبيق ويورث منApplication

    @Override
    public void start(Stage primaryStage) { // أول دالة تشتغل عند تشغيل البرنامج

        Label title = new Label("SEPP Platform"); // نص عنوان رئيسي
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22)); // تحديد نوع وحجم الخط وخليه عريض
        title.setTextFill(Color.web("#1a5276")); // تغيير لون النص

        Label subtitle = new Label("Select a module to open:"); // نص فرعي تحت العنوان
        subtitle.setFont(Font.font("Arial", 14)); // حجم خط أصغر
        subtitle.setTextFill(Color.web("#555555")); // لون رمادي خفيف

        Button btnCourse   = makeButton("Course Management",    "#1a5276"); // زر يفتح إدارة الكورسات
        Button btnQuestion = makeButton("Question Management","#117a65"); // زر لإدارة الأسئلة
        Button btnQuiz     = makeButton("Quiz Management", "#784212"); // زر لإدارة الكويز
        Button btnStudent  = makeButton("Student Management","#6c3483"); // زر لإدارة الطلاب

        // كل زر يفتح نافذة مستقلة
        btnCourse.setOnAction(e   -> new CourseForm().start(new Stage())); // عند الضغط يفتح نافذة الكورسات
        btnQuestion.setOnAction(e -> new QuestionForm().start(new Stage())); // يفتح نافذة الأسئلة
        btnQuiz.setOnAction(e     -> new QuizForm().start(new Stage())); // يفتح نافذة الكويز
        btnStudent.setOnAction(e  -> new StudentForm().start(new Stage())); // يفتح نافذة الطلاب

        VBox root = new VBox(15,
                title, subtitle,
                btnCourse, btnQuestion, btnQuiz, btnStudent);  // ترتيب العناصر عمودي مع مسافة 15
        root.setPadding(new Insets(40)); // مسافة داخلية حول المحتوى
        root.setAlignment(Pos.CENTER); // توسيط العناصر بالنص
        root.setStyle("-fx-background-color:#f4f6f7;"); // لون خلفية النافذة

        primaryStage.setScene(new Scene(root, 400, 340)); // إنشاء الشاشة وتحديد حجمها
        primaryStage.setTitle("SEPP - Software Engineering Practice Platform"); // عنوان النافذة
        primaryStage.show(); // عرض النافذة للمستخدم
    }

    private Button makeButton(String text, String color) { // دالة تسوي زر جاهز بنفس الشكل
        Button b = new Button(text); // إنشاء زر جديد بالنص
        b.setPrefWidth(320); // عرض ثابت للزر
        b.setStyle("-fx-background-color:" + color + "; -fx-text-fill:white;"
                + "-fx-font-size:13px; -fx-padding:10 20; -fx-background-radius:6;"); // تنسيق الزر
        return b; // رجّع الزر بعد تنسيقه
    }

    public static void main(String[] args) { launch(args); } // تشغيل البرنامج
}


