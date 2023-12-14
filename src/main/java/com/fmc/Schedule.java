package com.fmc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule  extends Student{

ArrayList<Course> ScheCourse=new ArrayList<>();;

private LocalDate day;
private LocalDate time;
private Semester semester;
private Faculty faculty;
Student student;


//الاريي تاعت كورسات الستيودنت راح نقرأها من فايل
public Schedule(LocalDate day, LocalDate end,  Semester semester, Faculty faculty,Student student) {
       super();
    this.day = day;
        this.time = end;
        this.semester = semester;
        this.faculty = faculty;
       this.ScheCourse=student.StuCourse ;
    }
public Schedule(LocalDate day, LocalDate time,  Semester semester, Faculty faculty,Staff staff) {
       super();
    this.day = day;
        this.time = time;
        this.semester = semester;
        this.faculty = faculty;
       this.ScheCourse=staff.staffCourseArray ;
    }

public LocalDate getDay() {
    return day;
}
public void setDay(LocalDate day) {
    this.day = day;
}
public LocalDate getTime() {
    return time;
}
public void setTime(LocalDate time) {
    this.time = time;
}
public ArrayList<Course> getCourse() {
    return ScheCourse;
}
public void setCourse(Course course) {
   this.ScheCourse.add(course);
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









@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Schedule{");
    sb.append("day=").append(day);
    sb.append(", time=").append(time);
    sb.append(", semester=").append(semester);
    sb.append(", faculty=").append(faculty);
    sb.append(", student=").append(student);
    sb.append(", courses=").append(ScheCourse);
    sb.append('}');
    return sb.toString();
}

}