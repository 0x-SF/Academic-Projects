/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stage2;

// Developer: Nawaf Abdullah Al-Ameeri | ID: ********
// SEPP Project - Stage 2
// كلاس Student يمثل الطالب المسجّل

import java.util.ArrayList;

public class Student {

    private String studentId;
    private String name;
    private String email;
    private double gpa;
    private int totalPoints;
    private ArrayList<String> enrolledCourses;

    public Student(String studentId, String name, String email, double gpa) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name required");

        if (!email.contains("@"))
            throw new IllegalArgumentException("Invalid email");

        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
        this.totalPoints = 0;
        this.enrolledCourses = new ArrayList<>();
    }

    public void addPoints(int points) { // إضافة نقاط
        if (points > 0)
            this.totalPoints += points;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getGpa() {
        return gpa;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
