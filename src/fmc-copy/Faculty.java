package com.fmc;

import java.util.ArrayList;

public class Faculty extends Course {
    

private String name;
private ArrayList<Course>  course;
private String contact;


public Faculty(String name, ArrayList<Course> course) {
    this.name = name;
    this.course = course;
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public ArrayList<Course> getCourse() {
    return course;
}


public void setCourse(ArrayList<Course> course) {
    this.course = course;
}


public String getContact() {
    return contact;
}


public void setContact(String contact) {
    this.contact = contact;
}











}
