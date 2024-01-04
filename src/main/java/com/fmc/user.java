package com.fmc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;

public class user {
    // protected Map<Faculty, ArrayList<Course> > FacultyCourse = new HashMap<>();
    static Map<Semester, ArrayList<Course> > semesterCourse = new HashMap<>();
     static Map<Staff, ArrayList<Course> > staffCourse = new HashMap<>();
    static Map<Student, ArrayList<Course> > courseStudent = new HashMap<>(); //student courses
   static  Map<Faculty, ArrayList<Course> > FacultyCourse = new HashMap<>();
    static ArrayList<Course> CourseList=new ArrayList<>();;

   public static Map<Semester, ArrayList<Course>> getSemesterCourse() {
    return semesterCourse;
}

public static Map<Staff, ArrayList<Course>> getStaffCourse() {
    return staffCourse;
}

public static Map<Student, ArrayList<Course>> getCourseStudent() {
    return courseStudent;
}

public static Map<Faculty, ArrayList<Course>> getFacultyCourse() {
    return FacultyCourse;
}
// print the available courses
public  String AvaliableCourses() {

  CourseList.stream().distinct() .map(e->e.getStudent().size()<29 ?  e.getName():null).filter( m->m!=null).forEach(e->System.out.println(e));
       return "";}


}