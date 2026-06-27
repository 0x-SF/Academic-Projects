/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project – Stage 3
// كلاس Question يمثل السؤال في الاختبار

import java.util.ArrayList;

public class Question {

    // ====== الخصائص (5 attributes) ======
    private String        questionId;     // معرف السؤال
    private String        questionText;   // نص السؤال
    private ArrayList<String> options;    // الخيارات
    private String        correctAnswer;  // الإجابة الصحيحة
    private String        difficulty;     // مستوى الصعوبة (Easy / Medium / Hard)

    // ====== Constructor ======
    public Question(String questionText, String correctAnswer, String difficulty) { // إنشاء سؤال جديد

        // شرط 1: نص السؤال لا يكون فارغاً
        if (questionText == null || questionText.trim().isEmpty())
            throw new IllegalArgumentException("Question text cannot be empty.");

        // شرط 2: الإجابة الصحيحة لا تكون فارغة
        if (correctAnswer == null || correctAnswer.trim().isEmpty())
            throw new IllegalArgumentException("Correct answer cannot be empty.");

        this.questionId    = "Q-" + System.currentTimeMillis(); // ID تلقائي
        this.questionText  = questionText.trim(); // تنظيف النص
        this.correctAnswer = correctAnswer.trim(); // تنظيف الإجابة
        this.difficulty    = difficulty; // تخزين الصعوبة
        this.options       = new ArrayList<>(); // تخزين الصعوبة
    }

    // ====== Methods ======

    // إضافة خيار
    public void addOption(String option) {
        if (option != null && !option.trim().isEmpty()) // شرط صحة الخيار
            options.add(option.trim()); // إضافته للقائمة
    }

    // التحقق من الإجابة
    public boolean checkAnswer(String answer) { 
        return correctAnswer.equalsIgnoreCase(answer.trim()); // مقارنة بدون حساسية حروف
    }

    // ====== Getters ======
    public String            getQuestionId()   { return questionId; } // يرجع ID
    public String            getQuestionText() { return questionText; } // يرجع السؤال
    public String            getCorrectAnswer(){ return correctAnswer; } // يرجع الإجابة
    public String            getDifficulty()   { return difficulty; } // يرجع الصعوبة
    public ArrayList<String> getOptions()      { return options; } // يرجع الخيارات

    @Override
    public String toString() { // عرض السؤال
        return String.format("Q: %-30s | Answer: %-10s | Level: %s | ID: %s",
                questionText, correctAnswer, difficulty, questionId); // تنسيق
    }
}

