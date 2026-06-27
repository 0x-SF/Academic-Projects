/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 3
// كلاس Student يمثل الطالب المسجّل

import java.util.ArrayList; // قائمة الكورسات

public class Student { // كلاس الطالب

    private String studentId; // رقم الطالب
    private String name; // اسم الطالب
    private String email; // الإيميل
    private double gpa; // المعدل
    private int    totalPoints; // النقاط
    private ArrayList<String> enrolledCourses; // الكورسات

    public Student(String studentId, String name, String email, double gpa) { // إنشاء طالب
        if (name == null || name.trim().isEmpty()) // تحقق الاسم
            throw new IllegalArgumentException("Student name cannot be empty.");
        if (email == null || !email.contains("@")) // تحقق الإيميل
            throw new IllegalArgumentException("Email must contain '@'.");

        this.studentId       = studentId; // ID
        this.name            = name.trim(); // اسم
        this.email           = email.trim(); // إيميل
        this.gpa             = gpa; // معدل
        this.totalPoints     = 0; // بداية النقاط صفر
        this.enrolledCourses = new ArrayList<>(); // قائمة كورسات
    }

    public boolean enrollInCourse(String courseName) { // تسجيل كورس
        if (enrolledCourses.contains(courseName)) return false; // إذا موجود نرفض
        enrolledCourses.add(courseName); // نضيف
        return true; // نجاح
    }

    public void addPoints(int points) { if (points > 0) this.totalPoints += points; } // إضافة نقاط

    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) this.gpa = gpa; // تعديل GPA
    }

    public String getStudentId()   { return studentId; } // ID
    public String getName()        { return name; } // اسم
    public String getEmail()       { return email; } // إيميل
    public double getGpa()         { return gpa; } // GPA
    public int    getTotalPoints() { return totalPoints; } // نقاط

    @Override
    public String toString() { // عرض الطالب
        return String.format("Student: %-20s | ID: %-12s | Email: %-25s | GPA: %.2f",
                name, studentId, email, gpa); // تنسيق
    }
}


