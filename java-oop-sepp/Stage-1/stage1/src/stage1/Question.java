/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage1;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project – Stage 1
// كلاس Question يمثل السؤال في الاختبار

import java.util.ArrayList;

public class Question {

    private String questionId; // رقم السؤال
    private String questionText; // نص السؤال
    private ArrayList<String> options; // خيارات
    private String correctAnswer; // إجابة

    public Question(String questionText, String correctAnswer, String difficulty) {

        this.questionId = "Q-001"; // ثابت
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = new ArrayList<>();
    }

    public String getQuestionText() { return questionText; } // جلب السؤال
}
