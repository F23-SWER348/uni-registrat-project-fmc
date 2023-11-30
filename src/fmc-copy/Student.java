package com.fmc;

public class Student extends Grade {
    
private String name;
private int id;
private String contact;
private double gpa;




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
public double getGpa() {
    return gpa;
}
public void setGpa(double gpa) {
    this.gpa = gpa;
}










}
