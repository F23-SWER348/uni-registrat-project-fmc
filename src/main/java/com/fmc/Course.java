package com.fmc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class Course extends user{// جربنا كل الميثود  و نجحوا 
    
private String name;
private String shortcut;
private  int credits;
private Staff staff;
private Semester semester; 
LocalTime start;
   LocalTime end;

//    private LocalDate day; مش كثير مفهومة
// protected Map<Course, ArrayList<Student> > studentCourse = new HashMap<>(); //طلاب الكورس 

// ArrayList =new ArrayList<>();
 BlockingQueue<Student> studentCourseArray = new LinkedBlockingQueue<>(30);

public Course(){
    CourseList.add(this);
};
public Course(String name, String shortcut, int credits, Staff staff, Semester semester,Faculty faculty) {
    super();
    this.name = name;
    this.shortcut = shortcut;
    this.credits = credits;
    this.staff = staff;
    this.semester = semester;
  CourseList.add(this);
    staff.addCourse(this);
    user.staffCourse.put(staff, staff.staffCourseArray);
    semester.c.add(this);
    user.semesterCourse.put(semester, semester.c);
    // this.studentCourse.put(this, studentCourseArray);//موقعها غلط 
    faculty.facultyCourseArray.add(this);
    user.FacultyCourse.put(faculty,faculty.facultyCourseArray);
}


public Course(String name, String shortcut,int credits, Semester semester,Faculty faculty) {
    super();
    CourseList.add(this);
    this.name = name;
    this.shortcut = shortcut;
    this.semester = semester;
    semester.c.add(this);
    user.semesterCourse.put(semester, semester.c);
        staff.staffCourseArray.add(this);
    user.staffCourse.put(staff, staff.staffCourseArray);
            // this.studentCourse.put(this, studentCourseArray);//موقعها غلط 

 
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public String getShortcut() {
    return shortcut;
}


public void setShortcut(String shortcut) {
    this.shortcut = shortcut;
}


public int getCredits() {
    return credits;
}


public void setCredits(int credits) {
    this.credits = credits;
}


public LocalTime getStart() {
    return start;
}


public void setStart(LocalTime start) {
    this.start = start;
}


public LocalTime getEnd() {
    return end;
}


public void setEnd(LocalTime end) {
    this.end=end;
}


public Semester getSemester() {
    return semester;
}


public void setSemester(Semester semester) {
    this.semester = semester;
}


public BlockingQueue<Student> getStudent() {///////////
    return studentCourseArray;
}


public void setStudent(Student student) {

    try {
        studentCourseArray.put(student);
        student.addCourse(this);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }//السعة تم حلها ب بلوكنج كيو 
}
public void removeStudent(int  studentId) {// اول اشي عملت جبنا الكيو طلعنا منها الستيودنت الي اي دي تاعهم نفس الاي دي الي عندي بعدين بالماب حذفتهم جبت كل واحد اله نفس الايديي و مستحت الطالب من هاد الكورس من كورسات الطالب و بعدها مسحت الطالب من هاد الكورس 
    // int index = findIndex(studentCourseArray, studentToFind);
    // int index =studentCourseArray.indexOf(studentId);
    // this.studentCourseArray.take(student);//السعة
    List<Student> stu=studentCourseArray.stream().filter(e->e.getId()==studentId).collect(Collectors.toList());
stu.stream().map(e->e.StuCourse.remove(e));   
 this.studentCourseArray.removeIf(s->s.getId()==studentId);

    //    student.course.remove(stu);
}
// اذا بنحب نعمل ابديت ستيودنت 



public String toStringWithStudent() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Course Name: ").append(name).append("\n");
    stringBuilder.append("Shortcut: ").append(shortcut).append("\n");
    stringBuilder.append("Credits: ").append(credits).append("\n");
    stringBuilder.append("Staff: ").append(staff.getName()).append("\n");
    stringBuilder.append("Semester: ").append(semester.getYear()).append("\n");

    stringBuilder.append("Students in the Course:\n");
    for (Student student : studentCourseArray) {
        stringBuilder.append("- ").append(student.getName()).append("\n\n\n\n");
    }

    return stringBuilder.toString();
}

@Override
public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Course Name: ").append(name).append("\n");
    stringBuilder.append("Shortcut: ").append(shortcut).append("\n");
    stringBuilder.append("Credits: ").append(credits).append("\n");
    stringBuilder.append("Staff: ").append(staff.getName()).append("\n");
    stringBuilder.append("Semester: ").append(semester.getYear()).append("\n");


    return stringBuilder.toString();
}



public String gradeABC(){

    return this.getCredits() == 4 ? "A" : this.getCredits() >= 3.5 ? "B+" : this.getCredits() >= 3 ? "B" :this.getCredits()<= 2.5 ? "C+" : this.getCredits() >= 2 ? "C" :this.getCredits() >= 1.5 ? "D+":this.getCredits() >= 1 ? "D"  :"I";

}





}
