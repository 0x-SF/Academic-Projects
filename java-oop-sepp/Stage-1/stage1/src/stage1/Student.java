/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage1;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 1
// كلاس Student يمثل الطالب المسجّل

import java.util.ArrayList;

public class Student {

    private String studentId; // رقم الطالب
    private String name; // الاسم
    private String email; // الإيميل
    private double gpa; // المعدل
    private int totalPoints; // نقاط
    private ArrayList<String> enrolledCourses; // كورسات

    public Student(String studentId, String name, String email, double gpa) {

        this.studentId = studentId; // تخزين مباشر
        this.name = name;
        this.email = email;
        this.gpa = gpa;
        this.totalPoints = 0;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() { return name; } // جلب الاسم
    public String toString() { return name; } // عرض بسيط
}
