package com.fmc;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.App.EvaluationTask;
import com.App.GPATask;

public class StudentTest {
    Student CelinaStu = new Student("celina", 2021094423, "fat@gmail");
    Student MaiStu = new Student("mai", 2021094423, "fat@gmail");

    Student FatmaStu = new Student("fatma", 202109442, "fat@gmail");
    Staff JG = new Staff("JG", "JG@gmail", "8520");

    Staff MB = new Staff("MB", "ghg@jjkh", "02115");
    Faculty science = new Faculty("science", "hjhj");
    Semester s2024 = new Semester("", 2023, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1));
    Course math1 = new Course("math1", "math131", 4, MB, s2024, science);
    Course math2 = new Course("math2", "math132", 4, MB, s2024, science, math1);
    Course math3 = new Course("math3", "math3", 3, JG, s2024, science);
    Course math4 = new Course("math4", "math4", 4, JG, s2024, science, math2);
    Grade Fgrade = new Grade(3.0, math1, FatmaStu);
    Grade Fgrade2 = new Grade(4.0, math2, FatmaStu);
    Grade Fgrade3 = new Grade(3.5, math2, MaiStu);
    Grade Fgrade4 = new Grade(2.5, math3, MaiStu);
    Schedule celinaSchedule = new Schedule(CelinaStu);
    Schedule MBSchedule = new Schedule(MB);
    Schedule JGSchedule = new Schedule(JG);

    private ExecutorService executorService;
    user user = new user();
    String s;

    @Before
    public void setup() throws InterruptedException {

        math2.setStudent(MaiStu);
        math3.setStudent(MaiStu);
        math1.setStudent(CelinaStu);
        math2.setStudent(CelinaStu);
        math2.setStart(LocalTime.of(7, 30, 0));
        math1.setStart(LocalTime.of(8, 0, 0));
        math3.setStart(LocalTime.of(9, 30, 0));
        math4.setStart(LocalTime.of(10, 0, 0));
        math3.setDay("Wednesday");
        math4.setDay("Saturday");

        math1.removeStudent(202109442);
        math2.setStudent(FatmaStu);
        executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            new GPATask().run();
        });

        executorService.execute(() -> {
            new EvaluationTask().run();
        });

        FatmaStu.evaluation(FatmaStu.GPA());
        s = MaiStu.StudentTranscripts().toString();

    }

    @Test
    public void GPA_TEST() {
        assertEquals(3.5, (double) FatmaStu.GPA(), 0);
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

    String expected = "Student Name: mai      Student ID: 2021094423       Contact: fat@gmail      \r\n" + //
            "name----Credits----grade----Mark estimation\r\n" + //
            "math3------3--------2.5---------C+\r\n" + //
            "math2------4--------3.5---------B+\r\n" + //
            "GPA:3.0714285714285716Yourevaluation:Honor";

    @Test
    public void transcripts() {
        assertEquals(expected.replaceAll("\\s", ""), s.replaceAll("\\s", ""));

    }

    @Test
    public void remove() {
        assertEquals(false, math1.getStudent().contains(FatmaStu));
    }

    @Test
    public void conflect() {
        assertEquals(true, celinaSchedule.conflect());
    }

    @Test
    public void getCourseStudent() {
        assertEquals(true, CelinaStu.getCourse().contains(math1) && CelinaStu.getCourse().contains(math2));
    }

    @Test
    public void getStudent() {
        assertEquals(true,
                math1.getStudent().stream().map(e -> e.getName()).collect(Collectors.toList()).contains("celina"));

    }

    @Test
    public void getCourseStaff() {
        assertEquals(true, MB.getCourse().contains(math1) && MB.getCourse().contains(math2));
    }

    // Dear "+student.getName()+" Done math2.setStudent(MaiStu)
    @Test
    public void depended() {
        assertEquals("Dear mai you must take this course Course Name: math1---Shortcut: math131---Credits: 4---",
                math2.setStudent(MaiStu));
    }

    @Test
    public void addStudentIfTookTheRequiredCourses() {

        assertEquals("Dear mai you must take this course Course Name: math1---Shortcut: math131---Credits: 4---"
                .replaceAll("\\s", ""), math2.setStudent(MaiStu).replaceAll("\\s", ""));
    }

    @Test
    public void scheduleTest() {

        assertEquals(
                "Schedule StaffName=MB Email:ghg@jjkh,courses=math1,math131day=null,time=08:00to09:00,courses=math2,math132day=null,time=07:30to08:30 schedule have a conflect? true"
                        .replaceAll("\\s", ""),
                MBSchedule.toStringStaff().replaceAll("\\s", ""));
    }

    @Test
    public void StudentInfoTest() {

        assertEquals("Student Name: fatma Student ID: 202109442 Contact: fat@gmail GPA: 3.5".replaceAll("\\s", ""),
                FatmaStu.StudentInfo().replaceAll("\\s", ""));
    }

}
