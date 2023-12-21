package com;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
   public static Student FatmaStu = new Student("fatma", 202109442, "fat@gmail");
  public static void main(String[] args) {
user user = new user();
    System.out.println("read from file and return the info in array list ");
    StudentReader sr = new StudentReader("assets\\data\\student.json", "assets\\data\\course.json",
        "assets\\data\\Semester.json", "assets\\data\\staff.json", "assets\\data\\faculty.json");
    ArrayList<Faculty> facultys = sr.readFaculty();
    ArrayList<Staff> staffs = sr.readStaff();
    ArrayList<Semester> semester = sr.readSemester();
    ArrayList<Course> course = sr.readCourse();
    ArrayList<Student> student = sr.readStudent();

    System.out.println("the courses that are taught by FQ");
    Optional<Staff> f = staffs.stream().filter(e -> e.getName().equals("FQ")).findFirst();
    System.out.println(f.get().getCourse());

    // هان دخلنا بالبداية اكم مستخدم عادي عشان نجرب الميثود
    int year = LocalDate.now().getYear();
    LocalDate DsetDay = LocalDate.of(2021, 1, 1);
    LocalDate end = LocalDate.of(2023, 1, 1);

    Staff Fatma = new Staff("fatma", "Fatma@jjkh", "02115");
    Staff Mai = new Staff("Mai", "ghg@jjkh", "02115");
    Faculty science = new Faculty("science", "hjhj");
    Faculty science2 = new Faculty("science2", "hjhj");
    Semester s2023 = new Semester("", year, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));
    Semester s2024 = new Semester("", year, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));

    Schedule MaiSchedula = new Schedule(Mai);
    Schedule FatmaSchedula = new Schedule(Fatma);
    Course math1 = new Course("Math1", "math131", 3, Fatma, s2024, science);
    Course math2 = new Course("Math2", "math132", 4, Fatma, s2024, science, math1);
    Course math4 = new Course("Math4", "math134", 34, Mai, s2023, science2);
    Course math6 = new Course("Math6", "math136", 36, Mai, s2023, science2);
    
    Stream.of(science.getCourse()).forEach(e -> System.out.print("\n The Science Courses :\n " + e.toString() + "\n"));// نجحت
    Stream.of(s2023.getCourse()).forEach(e -> System.out.print("\n the s2023 courses:\n " + e.toString()));// نجحت
    Stream.of(Mai.getCourse()).forEach(e -> System.out.print("\n the Mai staff courses:\n " + e.toString()));// نجحت

    ArrayList<Course> s = Mai.getCourse();
    System.out.println("\n another way to print Mai Staff courses \n " + s.toString());
    Student MaiStu = new Student("mai", 2021094423, "fat@gmail");
    Student CelinaStu = new Student("celina", 2021094423, "fat@gmail");
    System.out.println("We will add students to the course if the student has taken the previous required courses");//بضيف الطالب للكورس اذا كان ماخد الكورسات المعتمدة عليه
                                                                                                                 
    System.out.println(math2.setStudent(CelinaStu));
    math1.setStart(LocalTime.of(8, 0, 0));
    System.out.println(math1.setStudent(FatmaStu));
    System.out.println(math2.setStudent(FatmaStu));
    System.out.println(math2.setStudent(MaiStu));

    math2.setStart(LocalTime.of(8, 30, 0));
    math2.setDay("monday");
    math1.setDay("monday");
    math2.setDay("monday");
    math1.setDay("monday");
    math4.setStart(LocalTime.of(8, 0, 0));
    math6.setStart(LocalTime.of(9, 1, 0));
    System.out.println("to test if celina schedule have a conflect ?");
    Schedule celinaSchedule = new Schedule(CelinaStu);
    System.out.println(" time conflicts: " + celinaSchedule.conflect());// نجحت
    System.out.println("celina Schedule :\n" + celinaSchedule.toStringStu());// نجحت
    math1.removeStudent(202109442);// نجحت
    System.out.println("math1 students");
    math1.getStudent().stream().forEach(e -> System.out.println(e.getName()));// نجحت

    System.out.println("Mai courses :");
    MaiStu.getCourse().stream().forEach(e -> System.out.println(e));// نجحت

    System.out.println("staff Mai Schedule :\n" + MaiSchedula.toStringStaff());// نجحت
    System.out.println("Staff Mai courses :" + Mai.getCourse().toString());

    Grade Fgrade = new Grade(3.0, math1, FatmaStu);
    Grade Fgrade2 = new Grade(4.0, math2, FatmaStu);
    System.out.println("Student Fatma info \n" + FatmaStu.StudentInfo());
    System.out.println("Student Fatma GPA \n" + FatmaStu.GPA());
    try {
      System.out.println("Student Fatma Transcripts \n" + FatmaStu.StudentTranscripts());

      System.out.println("evaluation test \n" + FatmaStu.evaluation(3.0));
      System.out.println("Student Fatma evaluation \n" + FatmaStu.evaluation(FatmaStu.GPA()));
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    System.out.println("Staff Mai info :" + Mai.toString());
    System.out.println("Faculty  science info :" + science.toString());
    System.out.println("Avaliable courses");

   System.out.println(user.AvaliableCourses());
  


   ExecutorService ex=Executors.newCachedThreadPool();
       ex.execute(new GPATask());
        ex.execute(new EvaluationTask()); 
        
        ex.shutdown();
    

    ////////////// فحص النواقص

    // كورس ريجستريشن 1
    // كلاس سكاجويلنج1
    // 0ادارة تسجيل الطلاب //ما بقدر احطها ب فاكلتي معينة
    // 1تقرير عن كشف العلامات زي اخر كل فصل زي لما تطلع علاماتنا اخر الفصل
    // 1 نعمل نيو ستودينت و نيو فاكاتي
    // 1نعمل اشي يجيب معلومات الستيودت الي هما الاسم معلومات التواصل الرول اذا هو
    // 1 ستيودنت ولا ستاف
    // 1يسمح يعمل فصل جديد
    // 1 اليوم و البداية و النهاية و اكثر من موعد للكورس في == كيف يعمل نيو كورس عن
    // 1طريق اسم الكورس وعدد الساعات و اي فاكلتي تابع ويحط موعدها الاسبوعي الها
    // 1بقدر يسجل الطلاب ب الكلاس ،بس لازم يتأكد انو
    // 1يدور على الكورسات المتاحة و يشوف المتطلبات و يسجل الطلاب ب الحصص بس لازم
    // 1يتأكد انو الكرس ما بعتمد ع كورس مش مأخوذ
    // 1 مدير التسجيل يدخل علامات الطلاب بالكورسات تاعونهم
    // 1يتابع الحالة الاكاديمية للطالب و يقدم تقرير
    // 1لازم يطلعلي الجدول و يتأكد انو نا في تضارب اضا في يحكي
    // 1لازم بعض من الكلاسات يكونوا ثريد سيف
    // 1ولازم نشرح الجي بي ا على الباررل كومبيوت و ع كمان ميثود
    // 0.5اعمل تيست ل 13 ميثود  (8)
  

//celinaaaaaa

}


 public static class GPATask  implements Runnable {

  @Override
  public void run() {
     try{
    System.out.println("Student Fatma GPA \n" + FatmaStu.GPA());
     }catch(Exception e){

     }
  }

  
}


 public static class EvaluationTask  implements Runnable {

  @Override
  public void run() {
     try{
        System.out.println("Student Fatma evaluation \n" + FatmaStu.evaluation(FatmaStu.GPA()));

     }catch(Exception e){

     }
  }
}}