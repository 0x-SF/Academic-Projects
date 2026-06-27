/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stage1;
// SEPP Project - Stage 1 - Main Entry Point

public class Stage1 {

    public static void main(String[] args) {
        // إنشاء كائنات فقط للتجربة (بدون واجهة)
        Course course = new Course("Java", "Intro Course", "Dr A", 30); // إنشاء كورس
        Student student = new Student("S1", "Ali", "ali@test.com", 3.5); // إنشاء طالب
        Question question = new Question("What is Java?", "Programming", "Easy"); // سؤال
        Quiz quiz = new Quiz("Quiz 1", "Java", 30); // اختبار

        // طباعة النتائج فقط للتأكد من البيانات
        System.out.println(course); // عرض الكورس
        System.out.println(student); // عرض الطالب
        System.out.println(question.getQuestionText()); // عرض السؤال
        System.out.println(quiz.getTitle()); // عرض الكويز
    }
    
}
