/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stage2;

// SEPP Project - Stage 2 - Main Entry Point

public class Stage2 {

    public static void main(String[] args) {

        // إنشاء كورس
        Course course = new Course("Java", "OOP Course", "Dr A", 2);

        // إنشاء طلاب أولاً (مهم قبل الاستخدام)
        Student s1 = new Student("S001", "Nawaf", "nawaf@uqu.edu.sa", 3.5);
        Student s2 = new Student("S002", "Mohammed", "mohammed@uqu.edu.sa", 3.2);
        Student s3 = new Student("S003", "Ali", "ali@uqu.edu.sa", 3.8);
        Student s4 = new Student("S004", "Omar", "omar@uqu.edu.sa", 3.1);

        // تسجيل الطلاب (صح: نستخدم الاسم أو ID مو object)
        course.enrollStudent(s1.getName());
        course.enrollStudent(s2.getName());
        course.enrollStudent(s3.getName());
        course.enrollStudent(s4.getName());

        boolean result = course.enrollStudent("Ahmed");

        System.out.println("Enroll third student result: " + result);

        // اختبار النقاط على طالب واحد
        s1.addPoints(10);
        s1.addPoints(5);

        System.out.println("Student Points: " + s1.getTotalPoints());

        // إنشاء سؤال
        Question q = new Question("Java is?", "Programming", "Easy");
        q.addOption("Programming");
        q.addOption("Cooking");

        System.out.println("Answer check: " + q.checkAnswer("Programming"));

        // إنشاء Quiz
        Quiz quiz = new Quiz("Midterm", "Java", 20);
        quiz.addQuestion(q);

        System.out.println("Quiz activated: " + quiz.activate());
    }
}
