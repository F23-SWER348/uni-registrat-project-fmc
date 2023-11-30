package com.fmc;

import java.util.ArrayList;

public class Course {
    
private String name;
private String shortcut;
private static int credits;
private Staff staff;
private Faculty faculty;
private Semester semester;
public Object getName;
ArrayList<Student> student ;

public Course(String name, String shortcut, int credits, Staff staff, Faculty faculty, Semester semester) {
    this.name = name;
    this.shortcut = shortcut;
    this.credits = credits;
    this.staff = staff;
    this.faculty = faculty;
    this.semester = semester;
}


public Course(String name, String shortcut, Staff staff, Faculty faculty, Semester semester, Object getName,
        ArrayList<Student> student) {
    this.name = name;
    this.shortcut = shortcut;
    this.staff = staff;
    this.faculty = faculty;
    this.semester = semester;
    this.getName = getName;
    this.student = student;
}


public Course() {
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public String getShortcut() {
    return shortcut;
}


public void setShortcut(String shortcut) {
    this.shortcut = shortcut;
}


public static int getCredits() {
    return credits;
}


public void setCredits(int credits) {
    this.credits = credits;
}


public Staff getStaff() {
    return staff;
}


public void setStaff(Staff staff) {
    this.staff = staff;
}


public Faculty getFaculty() {
    return faculty;
}


public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
}


public Semester getSemester() {
    return semester;
}


public void setSemester(Semester semester) {
    this.semester = semester;
}


public ArrayList<Student> getStudent() {
    return student;
}


public void setStudent(Student student) {/// السعة 
    this.student.add(student);
}















}
