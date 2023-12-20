package com;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import com.fmc.Course;
import com.fmc.Faculty;
import com.fmc.Grade;
import com.fmc.Schedule;
import com.fmc.Semester;
import com.fmc.Staff;
import com.fmc.Student;
import com.fmc.user;
import com.parser.StudentReader;

public class App {

  public static void main(String[] args) {

System.out.println("read from file and return the info in array list ");
        StudentReader sr = new StudentReader("assets\\data\\student.json","assets\\data\\course.json","assets\\data\\Semester.json","assets\\data\\staff.json","assets\\data\\faculty.json");
       ArrayList<Faculty>facultys=       sr.readFaculty();
     ArrayList<Staff>staffs=  sr.readStaff();
      ArrayList<Semester>semester= sr.readSemester();
      ArrayList<Course>course= sr.readCourse();     
      ArrayList<Student>student= sr.readStudent();



      System.out.println("the courses that are taught by FQ");
     Optional<Staff> f=staffs.stream().filter(e->e.getName().equals("FQ")).findFirst();
System.out.println( f.get().getCourse());

// هان دخلنا بالبداية اكم مستخدم عادي عشان نجرب الميثود 
        int year = LocalDate.now().getYear();
        LocalDate DsetDay = LocalDate.of(2021, 1, 1);
        LocalDate end = LocalDate.of(2023, 1, 1);

       Staff Fatma = new Staff("fatma", "ghg@jjkh","02115" );
        Staff Mai = new Staff("Mai", "ghg@jjkh", "02115");
        Faculty science = new Faculty("science", "hjhj");
        Faculty science2 = new Faculty("science2", "hjhj");
        Semester s2023 = new Semester("",year, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));
        Semester s2024 = new Semester("",year, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));
        Schedule FatmaSchedual=new Schedule(Fatma);
        Course math1 = new Course("Math1", "math131", 3, Fatma, s2024, science);
        Course math2 = new Course("Math2", "math132", 4, Fatma, s2024, science);
        Course math4 = new Course("Math4", "math134", 34, Mai,s2023, science2);
        Course math6 = new Course("Math6", "math136", 36, Mai,s2023, science2);
        user user = new user();
        Stream.of(science.getCourse()).forEach(e->System.out.print("\n The Science Courses :\n "+e.toString()+"\n" ));// نجحت 
        Stream.of(s2023.getCourse()).forEach(e->System.out.print("\n the s2023 courses:\n "+e.toString()));//نجحت 
        Stream.of(Mai.getCourse()).forEach(e->System.out.print("\n the Mai staff courses:\n "+e.toString()));// نجحت
    
        ArrayList <Course> s=Mai.getCourse(); System.out.println("\n another way to print Mai Staff courses \n "+s.toString());
        Student FatmaStu = new Student("fatma", 202109442, "fat@gmail");
        Student MaiStu = new Student("mai", 2021094423, "fat@gmail");
        Student CelinaStu = new Student("celina", 2021094423, "fat@gmail");

        math1.setStudent(CelinaStu);
        math2.setStudent(CelinaStu);
        math1.setStart(LocalTime.of(8, 0, 0));
        math2.setStudent(FatmaStu);
        math1.setStudent(FatmaStu);
        math2.setStudent(MaiStu);
        math1.setStudent(MaiStu);
        math2.setStart(LocalTime.of(8, 30, 0));
        math2.setDay("monday");
        math1.setDay("monday");
        System.out.println("to test if celina schedule have a conflect ?");
        Schedule celinaSchedule = new Schedule(CelinaStu);
        System.out.println(" time conflicts: " + celinaSchedule.conflect());// نجحت
        System.out.println("celina Schedule :\n"+celinaSchedule.toString());// نجحت
         math1.removeStudent(202109442);// نجحت   
           System.out.println("math1 students");  
           math1.getStudent().stream().forEach(e->System.out.println( e.getName()));//نجحت

        System.out.println("Mai courses :");
        MaiStu.getCourse().stream().forEach(e->System.out.println(e));//نجحت
       
      
      

        Grade Fgrade = new Grade(3.0, math1, FatmaStu);
        Grade Fgrade2 = new Grade(4.0, math2, FatmaStu);
        System.out.println("Student Fatma info \n"+FatmaStu.StudentInfo());
      System.out.println("Student Fatma Transcripts \n"+FatmaStu.StudentTranscripts());
        System.out.println("Student Fatma GPA \n"+FatmaStu.GPA());
        System.out.println("evaluation test \n"+FatmaStu.evaluation(3.0));
        System.out.println("Student Fatma evaluation \n" + FatmaStu.evaluation(FatmaStu.GPA()));
        ////////////// فحص النواقص

        // كورس ريجستريشن 1
        // كلاس سكاجويلنج 0.5
        // ادارة تسجيل الطلاب //ما بقدر احطها ب فاكلتي معينة
        // تقرير عن كشف العلامات زي اخر كل فصل زي لما تطلع علاماتنا اخر الفصل
        // نعمل نيو ستودينت و نيو فاكاتي
        // نعمل اشي يجيب معلومات الستيودت الي هما الاسم معلومات التواصل الرول اذا هو
        // ستيودنت ولا ستاف
        // يسمح يعمل فصل جديد
        // اليوم و البداية و النهاية و اكثر من موعد للكورس في == كيف يعمل نيو كورس عن
        // طريق اسم الكورس وعدد الساعات و اي فاكلتي تابع ويحط موعدها الاسبوعي الها
        // بقدر يسجل الطلاب ب الكلاس ،بس لازم يتأكد انو
        // يدور على الكورسات المتاحة و يشوف المتطلبات و يسجل الطلاب ب الحصص بس لازم
        // يتأكد انو الكرس ما بعتمد ع كورس مش مأخوذ
        // مدير التسجيل يدخل علامات الطلاب بالكورسات تاعونهم
        // يتابع الحالة الاكاديمية للطالب و يقدم تقرير
        // لازم يطلعلي الجدول و يتأكد انو نا في تضارب اضا في يحكي
        // لازم بعض من الكلاسات يكونوا ثريد سيف
        // ولازم نشرح الجي بي ا على الباررل كومبيوت و ع كمان ميثود
        // اعمل تيست ل 13 ميثود
    }
}
