/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage3;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 3
// كلاس Course يمثل الدورة التعليمية

import java.util.ArrayList; // نستخدمها عشان نخزن قائمة الطلاب بشكل ديناميكي

public class Course { // كلاس يمثل المقرر الدراسي

    private String courseId; // رقم فريد لكل كورس
    private String name; // اسم الكورس
    private String description; // وصف الكورس
    private String instructorName; // اسم الدكتور
    private int    maxStudents; // الحد الأقصى للطلاب
    private ArrayList<String> enrolledStudents; // قائمة الطلاب المسجلين

    public Course(String name, String description, String instructorName, int maxStudents) { // إنشاء كورس جديد
        if (name == null || name.trim().isEmpty()) // نتأكد الاسم مو فاضي
            throw new IllegalArgumentException("Course name cannot be empty."); // خطأ لو الاسم فاضي
        if (maxStudents <= 0) // نتأكد العدد منطقي
            throw new IllegalArgumentException("Max students must be greater than 0."); // خطأ لو العدد غلط

        this.courseId         = "C-" + System.currentTimeMillis(); // نولد ID تلقائي باستخدام الوقت
        this.name             = name.trim(); // نخزن الاسم بعد تنظيف الفراغات
        this.description      = description; // نخزن الوصف
        this.instructorName   = instructorName; // نخزن اسم الدكتور
        this.maxStudents      = maxStudents; // نخزن الحد الأقصى
        this.enrolledStudents = new ArrayList<>(); // نسوي قائمة فاضية للطلاب
    }

    public boolean enrollStudent(String studentName) { // تسجيل طالب في الكورس
        if (enrolledStudents.size() >= maxStudents) return false; // إذا ممتلئ نرفض
        if (enrolledStudents.contains(studentName)) return false; // إذا موجود مسبقاً نرفض
        enrolledStudents.add(studentName); // نضيف الطالب للقائمة
        return true; // نجاح التسجيل
    }

    public boolean isFull()           { return enrolledStudents.size() >= maxStudents; } // يتحقق إذا الكورس ممتلئ
    public String getCourseId()       { return courseId; } // يرجع ID الكورس
    public String getName()           { return name; } // يرجع الاسم
    public String getDescription()    { return description; } // يرجع الوصف
    public String getInstructorName() { return instructorName; } // يرجع اسم الدكتور
    public int    getMaxStudents()    { return maxStudents; } // يرجع الحد الأقصى
    public int    getEnrolledCount()  { return enrolledStudents.size(); } // يرجع عدد المسجلين

    @Override
    public String toString() { // عرض الكورس بشكل مرتب
        return String.format("Course: %-20s | Instructor: %-15s | Max: %d", 
                name, instructorName, maxStudents); // تنسيق العرض
    }
}

