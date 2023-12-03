package com.fmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Student extends Grade {
    
private String name;
private int id;
private String contact;
private double gpa;
 Map<Course,Double> grade =new HashMap<>();
ArrayList<Course> course;


public Student(){};
public Student(String name, int id, String contact, double gpa) {
    super();
    this.name = name;
    this.id = id;
    this.contact = contact;
    this.gpa = gpa;
}


public Student(String name, int id, String contact) {
    this.name = name;
    this.id = id;
    this.contact = contact;
}







public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getContact() {
    return contact;
}
public void setContact(String contact) {
    this.contact = contact;
}







// public ArrayList<Course> getCourse() {
// ArrayList<Course> s= Stream.of(studentCourse).anyMatch(e->e.equals(this)).collect(collector.toList);

//     return course;
// }
public void setCourse(Course course) {
  this.course.add(course);
}
public Map<Course, Double> getGrade() {
    return grade;
}


public void setGrade(Map<Course, Double> grade) {
    this.grade = grade;
}




public Double GPA() {
    double sum = this.grade.values().stream().mapToDouble(e->e*Course.getCredits()).sum();//((Course)(grade.get(e)))
    return sum / grade.size();
}

//wrong
public Double GPA22() {
    double sum = this.grade.values().stream().mapToDouble(e->e*Course.getCredits()).sum();//((Course)(grade.get(e)))
    return sum / grade.size();
}





}
