package com.fmc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class Course extends user {// جربنا كل الميثود و نجحوا
    // mai
    private String name;
    private String shortcut;
    private int credits;
    private Staff staff;
        private Faculty faculty;
    private Semester semester;
    LocalTime start;
    LocalTime end;
DayOfWeek day ;
Course depend;
private int Capacity;
    // private LocalDate day; مش كثير مفهومة
    // protected Map<Course, ArrayList<Student> > studentCourse = new HashMap<>();
    // //طلاب الكورس

    // ArrayList =new ArrayList<>();
    public BlockingQueue<Student> studentCourseArray = new LinkedBlockingQueue<>(30);

   

    public Course(String name, String shortcut, int credits, Staff staff, Semester semester, Faculty faculty) {
        super();
        this.name = name;
        this.shortcut = shortcut;
        this.credits = credits;
        this.staff = staff;
        this.semester = semester;
       user. CourseList.add(this);
        staff.addCourse(this);
        user.staffCourse.put(staff, staff.staffCourseArray);
        semester.c.add(this);
        user.semesterCourse.put(semester, semester.c);
        // this.studentCourse.put(this, studentCourseArray);//موقعها غلط
       

        faculty.facultyCourseArray.add(this);
        user.FacultyCourse.put(faculty, faculty.facultyCourseArray);
    }  
     public Course(String name, String shortcut, int credits, Staff staff, Semester semester, Faculty faculty,Course course) {
        super();
        this.name = name;
        this.shortcut = shortcut;
        this.credits = credits;
        this.staff = staff;
        this.semester = semester;
        user. CourseList.add(this);
        staff.addCourse(this);
        user.staffCourse.put(staff, staff.staffCourseArray);
        semester.c.add(this);
        user.semesterCourse.put(semester, semester.c);
       faculty.facultyCourseArray.add(this);
        user.FacultyCourse.put(faculty, faculty.facultyCourseArray);
        this.depend=course;
    }

    public Course(String name, String shortcut, int credits, Semester semester, Faculty faculty) {
        super();
      user.   CourseList.add(this);
        this.name = name;
        this.shortcut = shortcut;
        this.semester = semester;
        semester.c.add(this);
        user.semesterCourse.put(semester, semester.c);
        faculty.facultyCourseArray.add(this);
      user.FacultyCourse.put(faculty, faculty.facultyCourseArray);

        staff.staffCourseArray.add(this);
        user.staffCourse.put(staff, staff.staffCourseArray);
        // this.studentCourse.put(this, studentCourseArray);//موقعها غلط

    }
 public Course() {
    };
    public String getName() {
        return name;
    }
    

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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

    public String gradeABC(Double grade) {

        return grade == 4 ? "A"
                : grade>= 3.5 ? "B+"
                        : grade >= 3 ? "B"
                                : grade <= 2.5 ? "C+"
                                        : grade >= 2 ? "C"
                                                : grade >= 1.5 ? "D+" : grade >= 1 ? "D" : "I";

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
        return end=start.plusHours(1);
    }

    public void setEnd(int end) {
        this.end=start.plusHours(end);
    }
    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(String Day) {
      
      day=DayOfWeek.valueOf(Day.toUpperCase());
    }


    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    


    public Course getDepend() {
        return depend;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public BlockingQueue<Student> getStudent() {///////////

        return studentCourseArray;
    }

    public String setStudent(Student student) {
        if (this.depend==null){
         studentCourseArray.offer(student);
            student.addCourse(this);
            Capacity++;
                    return "Dear "+student.getName()+" Done";

        }
        else{
        int x=  (int) student.getCourse().stream().filter(e->e.equals(this.getDepend())).count();
    
            if (x==1){
        try {
            studentCourseArray.offer(student);
            student.addCourse(this); 
        return "Dear "+student.getName()+" Done";
        } catch (Exception e) {
            e.printStackTrace();
        } // السعة تم حلها ب بلوكنج كيو
    } 
           return "Dear "+student.getName()+" you must take this course "+this.getDepend();}}
       
 

    public void removeStudent(int studentId) {// اول اشي عملت جبنا الكيو طلعنا منها الستيودنت الي اي دي تاعهم نفس الاي
                                              // دي الي عندي بعدين بالماب حذفتهم جبت كل واحد اله نفس الايديي و مستحت
                                              // الطالب من هاد الكورس من كورسات الطالب و بعدها مسحت الطالب من هاد الكورس
        // int index = findIndex(studentCourseArray, studentToFind);
        // int index =studentCourseArray.indexOf(studentId);
        // this.studentCourseArray.take(student);//السعة
        List<Student> stu = studentCourseArray.stream().filter(e -> e.getId() == studentId)
                .collect(Collectors.toList());
        stu.stream().map(e -> e.getCourse().remove(e));
        this.studentCourseArray.removeIf(s -> s.getId() == studentId);

        // student.course.remove(stu);
    }
    // اذا بنحب نعمل ابديت ستيودنت
// public void printAllCourse(){
//       CourseList.stream().forEach(e->System.out.println(e.getStudent().size()));;
// }
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
        stringBuilder.append("Course Name: ").append(name).append("---");
        stringBuilder.append("Shortcut: ").append(shortcut).append("---");
        stringBuilder.append("Credits: ").append(credits).append("---");
        // stringBuilder.append("Staff: ").append(staff.getName()).append("---");
        // stringBuilder.append("Semester: ").append(semester.getYear()).append("\n");

        return stringBuilder.toString();
    }

}
