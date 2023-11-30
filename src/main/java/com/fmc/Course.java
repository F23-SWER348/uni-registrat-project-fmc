package com.fmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course extends Faculty{
    
private String name;
private String shortcut;
private static int credits;
private Staff staff;
private Semester semester;
ArrayList<Student> student ;
protected Map<Course, ArrayList<Course> > semesterCourse = new HashMap<>();
ArrayList<Course> c=new ArrayList<>();
public Course(){};
public Course(String name, String shortcut, int credits, Staff staff, Semester semester,Faculty faculty) {
    super();
    this.name = name;
    this.shortcut = shortcut;
    this.credits = credits;
    this.staff = staff;
    this.semester = semester;
   this.c.add(this);
    this.semesterCourse.put(semester, c);
}



public Course(String name, String shortcut,int credits, Semester semester,Faculty faculty) {
    super();
    this.name = name;
    this.shortcut = shortcut;
    this.semester = semester;
     this.c.add(this);
    this.semesterCourse.put(semester, c);
 
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




public Semester getSemester() {
    return semester;
}


public void setSemester(Semester semester) {
    this.semester = semester;
}


public ArrayList<Student> getStudent() {///////////
    return student;
}


public void setStudent(Student student) {
    this.student.add(student);//السعة
}















}
