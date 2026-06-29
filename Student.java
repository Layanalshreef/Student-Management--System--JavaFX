
package com.mycompany.finalproject_ap;
public class Student {

    // Student information
    private String name;
    private String id;
    private String major;
    private String grade;

    // Constructor
    public Student(
            String name,
            String id,
            String major,
            String grade
    ) {

        this.name = name;
        this.id = id;
        this.major = major;
        this.grade = grade;
    }

    // Get student name
    public String getName() {
        return name;
    }

    // Get student ID
    public String getId() {
        return id;
    }

    // Get student major
    public String getMajor() {
        return major;
    }

    // Get student grade
    public String getGrade() {
        return grade;
    }
}
