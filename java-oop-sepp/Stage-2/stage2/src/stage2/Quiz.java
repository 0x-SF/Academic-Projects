/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage2;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 2
// كلاس Quiz يمثل الاختبار القصير

import java.util.ArrayList;

public class Quiz {

    private String quizId;
    private String title;
    private String courseName;
    private int durationMinutes;
    private boolean isActive;
    private ArrayList<Question> questions;

    public Quiz(String title, String courseName, int durationMinutes) {

        if (title.isEmpty())
            throw new IllegalArgumentException("Title required");

        this.quizId = "QZ-" + System.currentTimeMillis();
        this.title = title;
        this.courseName = courseName;
        this.durationMinutes = durationMinutes;
        this.isActive = false;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question q) {
        if (q != null)
            questions.add(q);
    }

    public boolean activate() {
        if (questions.isEmpty()) return false;
        isActive = true;
        return true;
    }
}
