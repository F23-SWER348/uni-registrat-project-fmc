package com.fmc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Staff  extends Student{
    
private String name;
private String email;
private long phoneNumber;
private ArrayList<Course> courses;
public Staff(String name, String email, long phoneNumber, ArrayList<Course> courses) {
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
public long getPhoneNumber() {
    return phoneNumber;
}
public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
}
public ArrayList<Course> getCourse() {///جاهزة
    List<Course>getCourses= courses.stream().filter(e->e.getStaff().equals(this)).collect(Collectors.toList());
    return (ArrayList<Course>) getCourses;
}
public void setCourse(Course course) {
    this.courses.add(course);
}
public void assignGrade(Student student,Course course,Double grade ) throws InterruptedException{// لازم اتأكد انو المادة موجودة جاهزة
    int testCours=(int) this.getCourse().stream().filter(e->e.equals(course)).count();
    ArrayList<Student>cour=course.getStudent();
    int test =(int) cour.stream().filter(e->e.equals(student)).count();
    if(test!=0&&testCours!=0){
ArrayBlockingQueue<Student> gpas=new ArrayBlockingQueue<>(2);
student.setGrade(course, grade);
gpas.put(student);}
else{
    System.out.println("not exist");
}

}
//برضو هبد

public void setCoursess(Course course) {
    this.courses.add(course);
}






}
