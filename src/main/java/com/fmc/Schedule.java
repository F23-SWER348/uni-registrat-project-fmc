package com.fmc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule  extends Student{

ArrayList<Course> ScheCourse=new ArrayList<>();;

private LocalDate day;
private LocalDate time;
ArrayList<Course>stuORstaffCourse;
Student student;


//الاريي تاعت كورسات الستيودنت راح نقرأها من فايل
public Schedule(Student student) {
       super();
 
      this.stuORstaffCourse=student.StuCourse;
    }
public Schedule(Staff staff) {
       super();
    
       this.stuORstaffCourse=staff.staffCourseArray ;
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
// public Semester getSemester() {
//     return semester;
// }
// public void setSemester(Semester semester) {
//     this.semester = semester;
// }
// public Faculty getFaculty() {
//     return faculty;
// }
// public void setFaculty(Faculty faculty) {
//     this.faculty = faculty;
// }


    
   
public boolean conflect(){
    // CourseList.stream().reduce((e1,e2)->e1.getStart()!=null&&e2.getStart()!=null&& !e1.getStart().isAfter(e2.getStart().plusHours(1)) && !e1.getStart().plusHours(1).isBefore(e2.getStart()));
    // return false;
    
    boolean noConflict = stuORstaffCourse.stream()
    .allMatch(course1 ->
            course1.getStart() != null &&
                    stuORstaffCourse.stream()
                            .filter(course2 -> course2.getStart() != null)
                            .noneMatch(course2 ->
                                    !course1.equals(course2) &&
                                            !course1.getStart().isAfter(course2.getStart().plusHours(1)) &&
                                            !course1.getStart().plusHours(1).isBefore(course2.getStart())
                            )
    );

System.out.println(" time conflicts: " + !noConflict);
return !noConflict;
}






@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Schedule{");
    sb.append("day=").append(day);
    sb.append(", time=").append(time);
    // sb.append(", semester=").append(semester);
    // sb.append(", faculty=").append(faculty);
    sb.append(", student=").append(student);
    sb.append(", courses=").append(ScheCourse);
    sb.append('}');
    return sb.toString();
}

}
