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
// هان بشوف اذا في عندي تضارب يعني 
    public boolean conflect() {
      
// اول اشي جبت كورسات الطالب او الاستاذ الي بدي اشوف اذا عنده تضارب بعدين بشوف اذا معين اله وقت بعدين جبت الكورس الثاني و شفت اذا معين اله وقت 
// وبعدين بفحص الاوقات يعني اذا الاول بخلص قبل ما تبلش الثانية و اذا الثاني ببلش قبل ما تخلص الاولى 
        boolean noConflict = stuORstaffCourse.stream()
                .allMatch(course1 -> course1.getStart() != null &&
                        stuORstaffCourse.stream()
                                .filter(course2 -> course2.getStart() != null)
                                .noneMatch(course2 -> !course1.equals(course2) &&
                                        !course1.getStart().isAfter(course2.getEnd()) &&
                                        !course1.getEnd().isBefore(course2.getStart())&&course1.getDay()==course2.getDay()));       
        return !noConflict;
    }

//    طبعنا جدول الطالب 

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
    //  طبعنا جدول المعلم
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
