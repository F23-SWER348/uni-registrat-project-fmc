package com;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.fmc.Course;
import com.fmc.Faculty;
import com.fmc.Grade;
import com.fmc.Schedule;
import com.fmc.Semester;
import com.fmc.Staff;
import com.fmc.Student;
import com.fmc.user;

public class main {
  

public static void main(String[] args) {
    int year=LocalDate.now().getYear();
    LocalDate start = LocalDate.of(2021, 1, 1);
        LocalDate end = LocalDate.of(2023, 1, 1);

    Staff Fatma=new  Staff("fatma", "ghg@jjkh", 02115);
        Staff Mai=new  Staff("Mai", "ghg@jjkh", 02115);
Faculty science=new Faculty("science", "hjhj");///// مسحنا اريي الكورس منها
Faculty science2=new Faculty("science2", "hjhj");///// مسحنا اريي الكورس منها
Semester s2023 = new Semester(year, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));
Semester s2024 = new Semester(year, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));
// Schedule FatmaSchedual=new Schedule(start, end, s2023, science, Fatma);

Course math1 = new Course("Math1", "math131", 3, Fatma,s2024, science);
Course math2 = new Course("Math2", "math132", 4, Fatma,s2024, science);
// Course math3 = new Course("Math3", "math133", 33, Fatma,s2024, science);
// Course math4 = new Course("Math4", "math134", 34, Mai,s2023, science2);
// Course math5 = new Course("Math5", "math135", 35, Mai,s2023, science2);
// Course math6 = new Course("Math6", "math136", 36, Mai,s2023, science2);
user user=new user();
// Stream.of(science.getCourse()).forEach(e->System.out.print("The Science Courses : 1"+e.toString()+"\n" ));// نجحت ما شاء الله 
// Stream.of(s2023.getCourse()).forEach(e->System.out.print(e.toString()));// نجحت ما شاء الله 
// Stream.of(Mai.getCourse()).forEach(e->System.out.print(e.toString()));// نجحت ما شاء الله 
// ArrayList <Course> s=Mai.getCourse();
// System.out.println(s.toString());
Student FatmaStu=new Student("fatma", 202109442, "fat@gmail");
Student MaiStu=new Student("mai", 2021094423, "fat@gmail");
Student CelinaStu=new Student("celina", 2021094423, "fat@gmail");
math1.setStudent(CelinaStu);
math2.setStudent(FatmaStu);
math1.setStudent(FatmaStu);
// System.out.println(FatmaSchedual.toString());//ما نجحت 
// math1.removeStudent(202109442);// الحمد لله نجاح باهر 
// Stream.of(math1.getStudent()).forEach(e->System.out.print("The Science Courses : 1"+e.toString()+"\n" )); // التجربة الاولى غلط
// math1.getStudent().forEach(e -> System.out.print("The Science Courses : 1" + e.toString() + "\n"));//نجحت الحمد الله هاي بتجيب طلاب المادة 
// FatmaStu.getCourse().stream().forEach(e->System.out.println(e));// زبطت الحمد لله بس كمان طبعت زيادة الطلاب الي بالمادة 
Grade Fgrade=new Grade(3.0, math1, FatmaStu);
Grade Fgrade2=new Grade(4.0, math2, FatmaStu);

System.out.println(FatmaStu.GPA());
System.out.println(FatmaStu.evaluation(3.0));
System.out.println(FatmaStu.evaluation(FatmaStu.GPA()));

}
}





