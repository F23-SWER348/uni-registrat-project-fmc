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
    // الاريي تاعت كورسات الستيودنت راح نقرأها من فايل
    public Schedule(Student student) {
        super();
this.student=student;
        this.stuORstaffCourse = student.StuCourse;
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

    public boolean conflect() {
        // CourseList.stream().reduce((e1,e2)->e1.getStart()!=null&&e2.getStart()!=null&&
        // !e1.getStart().isAfter(e2.getStart().plusHours(1)) &&
        // !e1.getStart().plusHours(1).isBefore(e2.getStart()));
        // return false;

        boolean noConflict = stuORstaffCourse.stream()
                .allMatch(course1 -> course1.getStart() != null &&
                        stuORstaffCourse.stream()
                                .filter(course2 -> course2.getStart() != null)
                                .noneMatch(course2 -> !course1.equals(course2) &&
                                        !course1.getStart().isAfter(course2.getEnd()) &&
                                        !course1.getEnd().isBefore(course2.getStart())&&course1.getDay()==course2.getDay()));

       
        return !noConflict;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule").append("  student Name =").append(student.getName()+"    id :"+student.getId()+"\n");
        stuORstaffCourse.stream().forEach(e->  sb.append(", courses=").append(e.getName()+","+e.getShortcut()+"   ").append("day=").append(e.getDay()).append(", time=").append(e.getStart()).append(" to "+e.getEnd()+"\n"));
        // Optional.ofNullable(stuORstaffCourse)
        // .ifPresent(courses -> courses.forEach(e -> {e->  sb.append(", courses=").append(e).append("day=").append(e.day).append(", time=").append(e.getStart()).append("to "+e.getEnd()))}));

      
        // sb.append(", semester=").append(semester);
        // sb.append(", faculty=").append(faculty);

              // sb.append('}');
         
        return sb.append("schedule have a conflect ? "+this.conflect()+"\n").toString();
    }

}
