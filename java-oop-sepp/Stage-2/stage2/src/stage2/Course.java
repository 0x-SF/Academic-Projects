/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage2;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 2
// كلاس Course يمثل الدورة التعليمية

import java.util.ArrayList;

public class Course {

    private String courseId;
    private String name;
    private String description;
    private String instructorName;
    private int maxStudents;
    private ArrayList<String> enrolledStudents;

    public Course(String name, String description, String instructorName, int maxStudents) {

        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name required"); // تحقق بسيط

        this.courseId = "C-" + System.currentTimeMillis(); // ID ديناميكي
        this.name = name;
        this.description = description;
        this.instructorName = instructorName;
        this.maxStudents = maxStudents;
        this.enrolledStudents = new ArrayList<>();
    }

    public boolean enrollStudent(String studentName) { // تسجيل طالب

        if (enrolledStudents.size() >= maxStudents) return false; // امتلاء
        if (enrolledStudents.contains(studentName)) return false; // تكرار
        enrolledStudents.add(studentName); // إضافة
        return true;
    }
}
