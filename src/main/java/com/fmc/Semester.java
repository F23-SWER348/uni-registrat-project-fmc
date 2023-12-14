package com.fmc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Semester extends Course{
   
    ArrayList<Course> c=new ArrayList<>();// c يعني semesterCourseArray

private int year;
private LocalDate start;
private LocalDate end;
public Semester(){}

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
    this.c.add(course);
    this.semesterCourse.put(this, c);
  // اتأكد انها شغالة
}
public void removeCourse(Course course){
 this.c.remove(course);
  
}

public void removeCourse(String Namecourse){
   
List<String> removes = this.c.stream().map(e -> e.getName().toLowerCase()).filter(e -> e.equals(Namecourse.toLowerCase())).collect(Collectors.toList());
this.c.remove(removes);//اتأكد اذا هي شغالةة
}





}