package com.fmc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class Schedule extends Student {

    ArrayList<Course> ScheCourse = new ArrayList<>();;

    // private LocalDate time;
    ArrayList<Course> stuORstaffCourse;
    Student student;
Staff staff;
    // the course's array will read here from file
    public Schedule(Student student) {
        super();
this.student=student;
        this.stuORstaffCourse = (ArrayList<Course>) student.getCourse();
    }

    public Schedule(Staff staff) {
        super();
this.staff=staff;
        this.stuORstaffCourse = staff.staffCourseArray;
    }



    // public LocalDate getTime() {
    //     return time;
    // }

    // public void setTime(LocalDate time) {
    //     this.time = time;
    // }

    public Staff getStaff() {
        return staff;
    }

    public ArrayList<Course> getCourse() {
        return ScheCourse;
    }

    public void setCourse(Course course) {
        this.ScheCourse.add(course);
    }
    // public Semester getSemester() {
    // return semester;
    // }
    // public void setSemester(Semester semester) {
    // this.semester = semester;
    // }
    // public Faculty getFaculty() {
    // return faculty;
    // }
    // public void setFaculty(Faculty faculty) {
    // this.faculty = faculty;
    // }
// here i check if there is conflict or not
    public boolean conflect() {
      
// First, I took the courses for the student or professor I wanted to see if he had a conflict. Then I looked to see if he had a set time. Then I took the second course and saw if he had a set time. Then I checked the times, meaning if the first ends before the second starts, and if the second starts before the first finishes.
        boolean noConflict = stuORstaffCourse.stream()
                .allMatch(course1 -> course1.getStart() != null &&
                        stuORstaffCourse.stream()
                                .filter(course2 -> course2.getStart() != null)
                                .noneMatch(course2 -> !course1.equals(course2) &&
                                        !course1.getStart().isAfter(course2.getEnd()) &&
                                        !course1.getEnd().isBefore(course2.getStart())&&course1.getDay()==course2.getDay()));       
        return !noConflict;
    }

//    printed the student schedule

    public String toStringStu() {
        try{
        StringBuilder sb = new StringBuilder();
        
        sb.append("Schedule").append("  student Name =").append(student.getName()+"    id :"+student.getId()+"\n");
        stuORstaffCourse.stream().forEach(e->  sb.append(", courses=").append(e.getName()+","+e.getShortcut()+"   ").append("day=").append(e.getDay()).append(", time=").append(e.getStart()).append(" to "+e.getEnd()+"\n"));
        // Optional.ofNullable(stuORstaffCourse)
        // .ifPresent(courses -> courses.forEach(e -> {e->  sb.append(", courses=").append(e).append("day=").append(e.day).append(", time=").append(e.getStart()).append("to "+e.getEnd()))}));

      
        // sb.append(", semester=").append(semester);
        // sb.append(", faculty=").append(faculty);

              // sb.append('}');
         
        return sb.append("schedule have a conflect ? "+this.conflect()+"\n").toString();
    }catch(Exception s){return"enter the time of each course ";}
    }
    //  printed the teacher's schedule
    public String toStringStaff() {
        try{
        StringBuilder sb = new StringBuilder();
        
        sb.append("Schedule").append("  Staff Name =").append(staff.getName()+"    Email :"+staff.getEmail()+"\n");
        stuORstaffCourse.stream().forEach(e->  sb.append(", courses=").append(e.getName()+","+e.getShortcut()+"   ").append("day=").append(e.getDay()).append(", time=").append(e.getStart()).append(" to "+e.getEnd()+"\n"));
        // Optional.ofNullable(stuORstaffCourse)
        // .ifPresent(courses -> courses.forEach(e -> {e->  sb.append(", courses=").append(e).append("day=").append(e.day).append(", time=").append(e.getStart()).append("to "+e.getEnd()))}));

      
        // sb.append(", semester=").append(semester);
        // sb.append(", faculty=").append(faculty);

              // sb.append('}');
         
        return sb.append("schedule have a conflect ? "+this.conflect()+"\n").toString();}
        catch(Exception s){return"enter the time of each course ";}
    }


}
