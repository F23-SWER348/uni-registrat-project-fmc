package com.fmc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule  extends Student{


private LocalDate day;
private LocalTime time;
private Semester semester;
private Faculty faculty;
Student student;



//الاريي تاعت كورسات الستيودنت راح نقرأها من فايل
public Schedule(LocalDate day, LocalTime time,  Semester semester, Faculty faculty,Student student) {
       super();
    this.day = day;
        this.time = time;
        this.semester = semester;
        this.faculty = faculty;
    }






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
// public ArrayList<Course> getCourse() {
//     return student.getCourse();
// }
public void setCourse(Course course) {
    student.setCourse(course);
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
