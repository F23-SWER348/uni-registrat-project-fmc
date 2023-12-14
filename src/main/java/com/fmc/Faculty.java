package com.fmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Faculty extends user {
    
//intfatmaaagit 
private String name;
private String contact;
ArrayList<Course> facultyCourseArray=new ArrayList<>();/////////////


public Faculty(String name,String contact) {
    super();
    this.name = name;
    this.contact=contact;
    
}
public Faculty() {};


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}



public String getContact() {
    return contact;
}


public void setContact(String contact) {
    this.contact = contact;
}

public ArrayList<Course> getCourse() {
   return facultyCourseArray;
}









}