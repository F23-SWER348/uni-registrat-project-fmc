package com.fmc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Semester extends Course{
   
    
private int year;
private LocalDate start;
private LocalDate end;


public Semester(int year, LocalDate start, LocalDate end) {
    super();
    this.year = year;
    this.start = start;
    this.end = end;
}

public int getYear() {
    return year;
}

public void setYear(int year) {
    this.year = year;
}

public LocalDate getStart() {
    return start;
}

public void setStart(LocalDate start) {
    this.start = start;
}

public LocalDate getEnd() {
    return end;
}

public void setEnd(LocalDate end) {
    this.end = end;
}

public ArrayList<Course> getCourse() {
    return semesterCourse.get(this);

}



public void createNewCourseinSemester(String name,String shortcut, int credits, Faculty faculty){

    Course course = new Course(name, shortcut,credits,this, faculty );
    this.c.add(this);
    this.semesterCourse.put(this, c);
  // اتأكد انها شغالة
}
public void removeCourse(Course course){
    courses.remove(course);
}

public void removeCourse(String course){
   
List<String> remove = courses.stream().map(e -> e.getName().toLowerCase()).filter(e -> e.equals(course.toLowerCase())).collect(Collectors.toList());
courses.remove(remove);
}










}
