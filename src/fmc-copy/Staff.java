package com.fmc;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Staff  extends Course{
    
private String name;
private String email;
private int phoneNumber;
private ArrayList<Course> courses;
public Staff(String name, String email, int phoneNumber, ArrayList<Course> courses) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.courses = courses;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public int getPhoneNumber() {
    return phoneNumber;
}
public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
}
public ArrayList<Course> getCourse() {///جاهزة
    ArrayList<Course>getCourses= courses.stream().filter(e->e.getStaff().equals(this)).collect(Collectors.toList());
    return getCourses;
}
public void setCourse(Course course) {
    this.courses.add(course);
}
public void assignGrade(Student student,Course course,Double grade ){// لازم اتأكد انو المادة موجودة جاهزة
    boolean testCours=this.getCourse().stream().filter(e->e.equals(course));
    ArrayList<Student>cour=course.getStudent();
    boolean test =cour.stream().filter(e->e.equals(student));
    if(test&&testCours){
ArrayBlockingQueue<Student> gpas=new ArrayBlockingQueue<>(2);
student.setGrade(course, grade);
gpas.put(student);}
else{
    System.out.println("not exist");
}

}








}
