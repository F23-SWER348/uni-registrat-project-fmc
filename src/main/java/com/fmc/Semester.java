package com.fmc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Semester extends Course {

    ArrayList<Course> c = new ArrayList<>();// c means semesterCourseArray
    private String name;
    private int year;
    private LocalDate start;
    private LocalDate end;

    public Semester() {
    }

    public Semester(String name,int year, LocalDate start, LocalDate end) {
        super();
        this.year = year;
        this.start = start;
        this.end = end;
        this.name=name;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // public LocalDate getStart() {
    // return start;
    // }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    // public LocalDate getEnd() {
    // return end;
    // }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
// bring the semester's courses
    public ArrayList<Course> getCourse() {
        if (semesterCourse.get(this)==null) 
        System.out.println("this semester have not  any course");
       return semesterCourse.get(this);

    }


    public void createNewCourseinSemester(String name, String shortcut, int credits, Faculty faculty) {

        Course course = new Course(name, shortcut, credits, this, faculty);
        this.c.add(course);
        this.semesterCourse.put(this, c);
     
    }

    public void removeCourse(Course course) {
        this.c.remove(course);

    }
// remove course by using course's name
    public void removeCourse(String Namecourse) {

        List<String> removes = this.c.stream().map(e -> e.getName().toLowerCase())
                .filter(e -> e.equals(Namecourse.toLowerCase())).collect(Collectors.toList());
        this.c.remove(removes);
    }
// print semester information
    @Override
    public String toString() {
        
            return "Semester [c=" + c + ", name=" + name + ", year=" + year + ", start=" + start + ", end=" + end + "]";
            
        }
      
    // }
    // public Optional<String> toStringOptional() {
    //     return Optional.ofNullable(c)
    //             .map(cValue -> "Semester [c=" + cValue + ", name=" + name +
    //                     ", year=" + year + ", start=" + start + ", end=" + end + "]")
    //             .or(() -> Optional.of("Don't have any course in this semester"));
    // }

}