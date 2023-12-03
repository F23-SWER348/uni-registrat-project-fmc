package com.fmc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student extends Grade {
    
private String name;
private int id;
private String contact;
private double gpa;
 Map<Course,Double> grade =new HashMap<>();
 
// ArrayList<Course> course;


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






public List<Course> getCourse() {
     List<Course> s= (List<Course>) Stream.of(studentCourse).map(e -> Stream.of(e)).reduce((e1, e2) -> Stream.concat(e1, e2)).filter(e->e.equals(this)).get();
// ArrayList<Course> s= Stream.of(studentCourse).collect(Collectors.groupingBy(e->e. );
// .forEach((k,v)->Stream.of(v).filter(e->e.equals(this)));
// 
// .anyMatch(e->e.equals(this)).collect(collector.toList);

    return s;
}
public void setCourse(Course course) {
    studentCourse.get(course).add(this);
 
}
public Double getGrade(Course course) {
   return this.grade.get(course);
    
}


public void setGrade( Course c,Double b) {
    this.grade.put(c, b);
}




public Double GPA() {
    // grade.forEach((k,v)->k.getCredits * v);
    // double sum = Stream.of(this.grade)
    // .reduce(1,(k,v)->k *v)
    // .mapToDouble(e->e * Course.getCredits()).sum();//((Course)(grade.get(e)))
    // return sum / grade.size();
//    Stream.of( grade).forEach((k,v)->k.getCredits()*v).sum();
   
    // double sum = grade.entrySet().stream()
    // .mapToDouble(entry -> entry.getValue() * (Course)entry.getKey().getCredits()).sum();

double totalCredits = grade.keySet().stream().mapToDouble(e->((Course)e).getCredits()).sum();
// return sum / totalCredits;
return 0.0;
}




}
