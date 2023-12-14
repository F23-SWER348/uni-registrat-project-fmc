package com.fmc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Staff  extends Student{
   ArrayList<Course> staffCourseArray=new ArrayList<>();

private String name;
private String email;
private long phoneNumber;


public Staff(String name, String email, long phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    
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
public long getPhoneNumber() {
    return phoneNumber;
}
public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
}
public ArrayList<Course> getCourse(String staffName) {///جاهزة
  ArrayList<Course>st=staffCourse.get(this);
    return st;
    // List<Course>getCourses= st.stream().filter(e->e.getStaff().equals(this)).collect(Collectors.toList());
    //  (ArrayList<Course>) getCourses;
}
public void setCourse(Course course) {
    ArrayList<Course> temp =staffCourse.get(this);
    temp.add(course);
    staffCourse.put(this, temp);
   
}
// public void assignGrade(Student student,Course course,Double grade ) throws InterruptedException{// لازم اتأكد انو المادة موجودة جاهزة
    
//     int testCours=(int)( Stream.of(staffCourse).filter(e->e.equals(course)).count());
//     ArrayList<Student>cour=course.getStudent();
//     int test =(int) cour.stream().filter(e->e.equals(student)).count();
//     if(test!=0&&testCours!=0){
// ArrayBlockingQueue<Student> gpas=new ArrayBlockingQueue<>(2);
// student.setGrade(course, grade) ;
// gpas.put(student);}
// else{
//     System.out.println("not exist");
// }

// }




public ArrayList<Course> getCourse() {
    return staffCourseArray;
 }
 public void addCourse(Course course1){
    staffCourseArray.add(course1);
 }

}
