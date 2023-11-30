package com.fmc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule {







public Schedule(LocalDate day, LocalTime time, ArrayList<Course> course, Semester semester, Faculty faculty) {
        this.day = day;
        this.time = time;
        this.course = course;
        this.semester = semester;
        this.faculty = faculty;
    }



private LocalDate day;
private LocalTime time;
private ArrayList<Course> course;
private Semester semester;
private Faculty faculty;



public LocalDate getDay() {
    return day;
}
public void setDay(LocalDate day) {
    this.day = day;
}
public LocalTime getTime() {
    return time;
}
public void setTime(LocalTime time) {
    this.time = time;
}
public ArrayList<Course> getCourse() {
    return course;
}
public void setCourse(ArrayList<Course> course) {
    this.course = course;
}
public Semester getSemester() {
    return semester;
}
public void setSemester(Semester semester) {
    this.semester = semester;
}
public Faculty getFaculty() {
    return faculty;
}
public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
}




















}
