package com.fmc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Semester {
   
    
private LocalDate year;
private LocalDate start;
private LocalDate end;

private ArrayList<Course> courses;

public Semester(LocalDate year, LocalDate start, LocalDate end, ArrayList<Course> courses) {
    this.year = year;
    this.start = start;
    this.end = end;
    this.courses = courses;
}

public LocalDate getYear() {
    return year;
}

public void setYear(LocalDate year) {
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

public String getCourses() {
    return courses.toString();
}



@Override
public String toString() {
    return "Semester [year=" + year + ", start=" + start + ", end=" + end + ", course=" + courses.toString() + "]";
}


public void createNewCourse(String name, int credits, Faculty faculty){

    Course course = new Course();
    courses.add(course);
}
public void removeCourse(Course course){
    courses.remove(course);
}

public void removeCourse(String course){
   
List<String> remove = courses.stream().map(e -> e.getName().toLowerCase()).filter(e -> e.equals(course.toLowerCase())).collect(Collectors.toList());
courses.remove(remove);
}










}
