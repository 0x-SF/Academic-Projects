/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage1;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 1
// كلاس Quiz يمثل الاختبار القصير

import java.util.ArrayList;

public class Quiz {

    private String quizId; // رقم
    private String title; // عنوان
    private String courseName; // كورس
    private int durationMinutes; // مدة
    private ArrayList<Question> questions; // أسئلة

    public Quiz(String title, String courseName, int durationMinutes) {

        this.quizId = "QZ-001";
        this.title = title;
        this.courseName = courseName;
        this.durationMinutes = durationMinutes;
        this.questions = new ArrayList<>();
    }

    public String getTitle() { return title; }
}
