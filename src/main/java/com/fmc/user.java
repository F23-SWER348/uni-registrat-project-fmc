package com.fmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class user {
    //مش حاس اله فايدة هاد الكلاس
    // protected Map<Faculty, ArrayList<Course> > FacultyCourse = new HashMap<>();
    static Map<Semester, ArrayList<Course> > semesterCourse = new HashMap<>();
     static Map<Staff, ArrayList<Course> > staffCourse = new HashMap<>();
    static Map<Student, ArrayList<Course> > courseStudent = new HashMap<>(); //كورسات الطالب 
   static  Map<Faculty, ArrayList<Course> > FacultyCourse = new HashMap<>();
   public ArrayList<Course> CourseList=new ArrayList<>();;

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

public String AvaliableCourses() {
    System.out.println(CourseList.toString());
   CourseList.stream()
            .filter(e -> e.getStudent().size()<=29).forEach(e->System.out.println(e.toString()));
            
    return "";}

}