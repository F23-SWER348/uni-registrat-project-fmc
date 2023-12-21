package com.fmc;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.App.EvaluationTask;
import com.App.GPATask;

public class StudentTest {
    Student CelinaStu = new Student("celina", 2021094423, "fat@gmail");
    Student MaiStu = new Student("mai", 2021094423, "fat@gmail");

          Student FatmaStu = new Student("fatma", 202109442, "fat@gmail");  
           
     Staff MB = new Staff("fatma", "ghg@jjkh","02115" );
        Faculty science = new Faculty("science", "hjhj");
         Semester s2024 = new Semester("",2023, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));
        Course math1 = new Course("Math1", "math131", 3, MB, s2024, science);
        Course math2 = new Course("Math2", "math132", 4, MB, s2024, science ,math1);
   
            Grade Fgrade = new Grade(3.0, math1, FatmaStu);
        Grade Fgrade2 = new Grade(4.0, math2, FatmaStu);  
                            Schedule celinaSchedule = new Schedule(CelinaStu);  
     
                            


                            private ExecutorService executorService;
                            user user = new user();
 String s;

     @Before
     public void setup() throws InterruptedException{ 
   
         math1.setStudent(CelinaStu);
           math2.setStudent(CelinaStu);
                   math2.setStart(LocalTime.of(7, 30, 0));
                   math1.setStart(LocalTime.of(8, 0, 0));
                   math2.setStudent(MaiStu);
        
                executorService = Executors.newCachedThreadPool();
                executorService.execute(() -> {
                    new GPATask().run();
                });
        
                executorService.execute(() -> {
                    new EvaluationTask().run();
                });
            
            FatmaStu.evaluation(FatmaStu.GPA());
            s=FatmaStu.StudentTranscripts().toString();
  

        }

   @Test
   public void GPA_TEST() {
        assertEquals(  3.5714285714285716, (double)FatmaStu.GPA(), 0);  
            math1.removeStudent(202109442);

    }

   @Test


public void evaluationTest() throws InterruptedException {
        executorService = Executors.newCachedThreadPool();
    executorService.execute(new GPATask());
    executorService.execute(new EvaluationTask());
    executorService.shutdown();
    executorService.awaitTermination(20, TimeUnit.SECONDS); // انتظار 5 ثوانٍ على سبيل المثال

        assertEquals("Deans", FatmaStu.evaluation(FatmaStu.GPA()));
    
}


    String expected = "Student Name: fatma      Student ID: 202109442       Contact: fat@gmail      \r\n" + //
            "name----Credits----grade----Mark estimation\r\n" + //
            "Math1------3.0--------3---------B\r\n" + //
            "Math2------4.0--------4---------A\r\n" + //
            "GPA: 3.5714285714285716Your evaluation :Deans";

   

    @Test
    public void transcripts() {
        assertEquals(expected.replaceAll("\\s", ""),s.replaceAll("\\s", ""));
      
     }
   
       @Test
    public void remove() {
 assertEquals(false,math1.getStudent().contains(FatmaStu));
     }
         @Test
    public void conflect() {
 assertEquals(true,celinaSchedule.conflect());
     }
             @Test
    public void getCourseStudent() {
 assertEquals(true,CelinaStu.getCourse().contains(math1)&&CelinaStu.getCourse().contains(math2));
     }
      @Test
      public void getCourseStaff() {
 assertEquals(true,MB.getCourse().contains(math1)&&MB.getCourse().contains(math2));
     }

// Dear "+student.getName()+" Done  math2.setStudent(MaiStu)
 @Test
      public void depended() {
 assertEquals("Dear mai you must take this course Course Name: Math1---Shortcut: math131---Credits: 3---",math2.setStudent(MaiStu));
     }

// @Test
//       public void Avaliable() {
//  assertEquals("math swer history swerOS Math1 Math2 Math4 Math6".trim(),user.AvaliableCourses().trim());
//      }
// @Test
// public void Avaliable() {
//     String result = user.AvaliableCourses();

//     assertEquals("math swer history swerOS Math1 Math2 Math4 Math6".replaceAll("\\s", ""), result.replaceAll("\\s", ""));
// }

//الخلل انو بطبع ايري فاضية 

}
