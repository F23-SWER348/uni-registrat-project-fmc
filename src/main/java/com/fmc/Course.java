package com.fmc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class Course extends user {// We tried all the methods, and they succeeded.
  
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

 

   
     private  BlockingQueue<Student> studentCourseArray = new LinkedBlockingQueue<>(30);

   
// Constructor 
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
        
       

        faculty.facultyCourseArray.add(this);
        user.FacultyCourse.put(faculty, faculty.facultyCourseArray);
    }  
    // Constructor if in the pre-course course
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
// Constructor if you have not yet specified a teacher for the course
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
        

    }
    // Constructor if there is no information about the course and I want to enter them manually
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
//Find out what is the sign of the material using symbols
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
        try {
             this.start = start;
        } catch (Exception e) {
           
        }
       
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
      try {
          day=DayOfWeek.valueOf(Day.toUpperCase());
      } catch (Exception e) {
    }
    
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

    public  BlockingQueue<Student> getStudent() {///////////
            return studentCourseArray;
    }

    
    // public  List <Student> getStudent() {///////////

    //     return studentCourseArray.stream().filter(e->e.getName().equals(this.getName())).collect(Collectors.toList());
    // }
    // I will add a student to the course if he takes the materials approved for this course
    public String setStudent(Student student) {
        if (this.depend==null){
         studentCourseArray.offer(student);
            student.addCourse(this);
            Capacity++;
                    return "Dear "+student.getName()+" Done";

        }
        else{
                //Here, we took the student’s courses and checked whether he took the material based on this course or not, and we made a tally. If the count was one, it means he took it, and if it was zero, it means no.
            int x=  (int) student.getCourse().stream().filter(e->e.equals(this.getDepend())).count();
    
            if (x>=1){
        try {// Here, after I made sure that I took it, I added it to this course
            studentCourseArray.offer(student);
            student.addCourse(this); 
              Capacity++;
        return "Dear "+student.getName()+" Done";
        } catch (Exception e) {
            e.printStackTrace();
        } // The capacity is solved with BlockingQ
    } }
    // If I don't take it, I will get this message
           return "Dear "+student.getName()+" you must take this course "+this.getDepend();}
       
 

    public void removeStudent(int studentId) { 
       //The first thing I did was get the Q. We took out the student whose ID had the same ID as the one I had. Then I deleted them. I took each one with the same ID and deleted the student from this course from the student courses, and then I deleted the student from this course.
        // int index = findIndex(studentCourseArray, studentToFind);
        // int index =studentCourseArray.indexOf(studentId);
        // this.studentCourseArray.take(student);//Capasity
    
    List<Student> stu = studentCourseArray.stream()
        .filter(e -> e.getId() == studentId)
        .collect(Collectors.toList());

stu.forEach(e -> e.getCourse().remove(this));
        this.studentCourseArray.removeIf(s -> s.getId() == studentId);
        // student.course.remove(stu);
    }
    // If we would like to update, start as a student
// public void printAllCourse(){
//       CourseList.stream().forEach(e->System.out.println(e.getStudent().size()));;
// }
// print the course with it's Students
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
// print the information of the course
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
