/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage1;

// Developer: Nawaf Abdullah Al-Ameeri | ID: *********
// SEPP Project - Stage 1
// كلاس Course يمثل الدورة التعليمية

import java.util.ArrayList; // نستخدم قائمة للطلاب لكن بدون منطق معقد

public class Course {

    private String courseId; // رقم الكورس
    private String name; // اسم الكورس
    private String description; // وصف بسيط
    private String instructorName; // اسم الدكتور
    private int maxStudents; // عدد الطلاب
    private ArrayList<String> enrolledStudents; // قائمة الطلاب

    public Course(String name, String description, String instructorName, int maxStudents) {

        this.courseId = "C-001"; // ID ثابت في البداية (بدون وقت)
        this.name = name; // تخزين مباشر بدون تحقق
        this.description = description; // تخزين الوصف
        this.instructorName = instructorName; // تخزين الاسم
        this.maxStudents = maxStudents; // تخزين العدد
        this.enrolledStudents = new ArrayList<>(); // إنشاء قائمة فاضية
    }

    public String getName() { return name; } // جلب الاسم
    public String toString() { return name; } // عرض بسيط
}
