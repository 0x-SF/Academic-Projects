/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage2;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project – Stage 2
// كلاس Question يمثل السؤال في الاختبار

import java.util.ArrayList;

public class Question {

    private String questionId;
    private String questionText;
    private ArrayList<String> options;
    private String correctAnswer;
    private String difficulty;

    public Question(String questionText, String correctAnswer, String difficulty) {

        if (questionText.isEmpty())
            throw new IllegalArgumentException("Question required");

        this.questionId = "Q-" + System.currentTimeMillis();
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.options = new ArrayList<>();
    }

    public void addOption(String option) { // إضافة خيار
        if (option != null && !option.isEmpty())
            options.add(option);
    }

    public boolean checkAnswer(String answer) { // تحقق من إجابة
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
