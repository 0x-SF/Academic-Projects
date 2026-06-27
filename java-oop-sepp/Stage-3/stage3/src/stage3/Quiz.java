/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 3
// كلاس Quiz يمثل الاختبار القصير

import java.util.ArrayList; // قائمة الأسئلة

public class Quiz { // كلاس الاختبار

    private String quizId; // رقم الاختبار
    private String title; // عنوان الاختبار
    private String courseName; // اسم الكورس
    private int    durationMinutes; // مدة الاختبار
    private boolean isActive; // هل الاختبار شغال
    private ArrayList<Question> questions; // قائمة الأسئلة

    public Quiz(String title, String courseName, int durationMinutes) { // إنشاء كويز
        if (title == null || title.trim().isEmpty()) // تحقق من العنوان
            throw new IllegalArgumentException("Quiz title cannot be empty."); // خطأ
        if (durationMinutes <= 0) // تحقق من المدة
            throw new IllegalArgumentException("Duration must be greater than 0.");

        this.quizId          = "QZ-" + System.currentTimeMillis(); // ID تلقائي
        this.title           = title.trim();  // تخزين العنوان
        this.courseName      = courseName; // اسم الكورس
        this.durationMinutes = durationMinutes; // مدة الاختبار
        this.isActive        = false; // يبدأ غير مفعل
        this.questions       = new ArrayList<>(); // قائمة أسئلة فاضية
    }

    public void addQuestion(Question q) { if (q != null) questions.add(q); } // إضافة سؤال

    public boolean activate() { // تفعيل الاختبار
        if (questions.isEmpty()) return false; // لا يشتغل بدون أسئلة
        this.isActive = true; // تفعيل
        return true; // نجاح
    }

    public void deactivate()          { this.isActive = false; } // إيقاف الاختبار
    public String getQuizId()         { return quizId; } // ID
    public String getTitle()          { return title; } // عنوان
    public String getCourseName()     { return courseName; } // الكورس
    public int    getDuration()       { return durationMinutes; } // المدة
    public boolean isActive()         { return isActive; } // الحالة
    public int    getQuestionCount()  { return questions.size(); } // عدد الأسئلة

    @Override
    public String toString() {  // عرض الكويز
        return String.format("Quiz: %-20s | Course: %-15s | %d min | Active: %s",
                title, courseName, durationMinutes, isActive ? "Yes" : "No"); // تنسيق
    }
}


