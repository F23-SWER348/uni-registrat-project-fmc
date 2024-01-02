package com.fmc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.App.EvaluationTask;
import com.App.GPATask;
import com.parser.StudentReader;

public class test {
 StudentReader sr = new StudentReader("assets\\data\\student.json", "assets\\data\\course.json",
    "assets\\data\\Semester.json", "assets\\data\\staff.json", "assets\\data\\faculty.json");

 ArrayList<Faculty> facultys = sr.readFaculty();
ArrayList<Staff> staffs = sr.readStaff();
ArrayList<Semester> semester = sr.readSemester();
ArrayList<Course> course = sr.readCourse(); 
 ArrayList<Student> student = sr.readStudent();
    
    @Before
    public void setup() throws InterruptedException {    
  }

    @Test
    public void GPA_TEST() {
        assertEquals("[Mai  2.75,  Celina  2.35, Fatma  2.4285714285714284]".replaceAll("\\s", ""),student.stream().map(e->e.getName()+"  "+ e.GPA()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }


 @Test
    public void getStudentCourse() {
        assertEquals("[Mai  [Course Name: math---Shortcut: math1---Credits: 4---, Course Name: swerOS---Shortcut: swer2---Credits: 3---, Course Name: swer---Shortcut: swer1---Credits: 3---]".replaceAll("\\s", "") + 
                ",Celina  [Course Name: math---Shortcut: math1---Credits: 4---, Course Name: swerOS---Shortcut: swer2---Credits: 3---, Course Name: swer---Shortcut:" .replaceAll("\\s", "")+ 
                "swer1---Credits: 3---] " .replaceAll("\\s", "")+ 
                ",Fatma  [Course Name: math---Shortcut: math1---Credits: 4---, Course Name: swer---Shortcut: swer1---Credits: 3---]]" .replaceAll("\\s", ""), student.stream().map(e-> e.getName()+"  "+ e.getCourse().toString()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }



   String s= "[MB [Course Name: math---Shortcut: math1---Credits: 4---] ,JG [Course Name: history---Shortcut: hist1---Credits: 3---]  ,FQ [Course Name: swer---Shortcut: swer1---Credits: 3---, Course Name: swerOS---Shortcut: swer2---Credits: 3---]]" ;

 @Test
    public void getStaff() {
        assertEquals(s .replaceAll("\\s", ""), staffs.stream().map(e->e.getName()+"\n"+e.getCourse().toString()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }


    String w= "[MB    Scheduleenter the time of each course,JG    ScheduleSchedule  Staff Name =JG    Email :JG@bethlehem.edu, courses=history,hist1   day=TUESDAY, time=10:30 to 11:30schedule have a conflect ? false ,FQ    ScheduleSchedule  Staff Name =FQ    Email :FQ@bethlehem.edu, courses=swer,swer1   day=TUESDAY, time=10:30 to 11:30, courses=swerOS,swer2   day=TUESDAY, time=10:30 to 11:30schedule have a conflect ? true]";

    @Test
    public void staffSh() {
        assertEquals(w .replaceAll("\\s", ""), staffs.stream().map(e->new Schedule(e)).map(x->x.getStaff().getName()+" Schedule"+x.toStringStaff()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }


 String b= "[spring2023   [Course Name: history---Shortcut: hist1---Credits: 3---], spring2024   null ,   fall2023 [Course Name: math---Shortcut: math1---Credits: 4---, Course Name: swer---Shortcut: swer1---Credits: 3---, Course Name: swerOS---Shortcut: swer2---Credits: 3---]]";
     
 @Test
    public void getsemester() {
        assertEquals(b .replaceAll("\\s", ""), semester.stream().map(e->e.getName()+"   "+e.getCourse()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }


    String y="[science [Course Name: math---Shortcut: math1---Credits: 4---, Course Name: swer---Shortcut: swer1---Credits: 3---, Course Name: swerOS---Shortcut: swer2---Credits: 3---] ,Art [Course Name: history---Shortcut: hist1---Credits: 3---]]";

    
 @Test
 public void getfaculty() {
     assertEquals(y.replaceAll("\\s", ""),  facultys.stream().map(e->e.getName()+"\n"+e.getCourse().toString()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
 }

 String c="[Mai    StudentName:Mai  StudentID:1      Contact:234234     GPA:2.75,              Celina   StudentName:Celina        StudentID:2          Contact:20219356        GPA:2.35,        Fatma               StudentName:Fatma         StudentID:3       Contact:5464564       GPA:2.4285714285714284]";

 @Test
    public void infoStudent() {
        assertEquals(c .replaceAll("\\s", ""),student.stream().map(e->e.getName()+"  "+ e.StudentInfo()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }


// student.stream().filter(e->e.getName(Celina)).map(e->e.remove)


//  @Test
//     public void getremoveCoursefromStudent() {
//         assertNotEquals("swer",);
//     }

    @Test
    public void staffconflect() {
        assertEquals("[true,false,true]", staffs.stream().map(e->new Schedule(e).conflect()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }


 @Test
    public void StudentSh() {
        assertEquals("[enterthetimeofeachcourse,enterthetimeofeachcourse,enterthetimeofeachcourse]", student.stream().map(e->new Schedule(e)).map(x->
        x.toStringStu()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }
   @Test
    public void studentconflect() {
        assertEquals("[true,true,true]", student.stream().map(e->new Schedule(e).conflect()).collect(Collectors.toList()).toString().replaceAll("\\s", ""));
    }
}
