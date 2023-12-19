package com.parser;

import java.io.FileReader;
import java.time.LocalDate;
import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.simple.*;
import org.json.simple.parser.*;

import com.fmc.Course;
import com.fmc.Faculty;
import com.fmc.Semester;
import com.fmc.Staff;
import com.fmc.Student;

public class StudentReader {
    ArrayList<Semester> Semesters = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Staff> staffs = new ArrayList<>();
    ArrayList<Faculty> facultys = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

    final String path;

    final String path2;
    final String path3;
 final String pathStaff;
  final String pathFaculty;
    public StudentReader(String path, String path2, String path3,String pathStaff,String pathFaculty) {
        this.path = path;
        this.path2 = path2;
        this.path3 = path3;
        this.pathStaff=pathStaff;
          this.pathFaculty=pathFaculty;
    }

    public ArrayList<Student> readStudent() {// بترجع و بتجيب اريي الطلاب وكمان كورسات الطالب والعلامات
        JSONParser parser = new JSONParser();
        // System.out.println("Student");
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(path));
            for (Object object : a) {
                JSONObject studentObj = (JSONObject) object;

                String name = (String) studentObj.get("name");
                // double gpa = (double) studentObj.get("gpa");
                String contact = (String) studentObj.get("contact");
                int id = ((Long) studentObj.get("id")).intValue();
                Student student = new Student(name, id, contact);

                if (studentObj.get("course") instanceof JSONArray) {
                    ArrayList courseArray = (ArrayList) studentObj.get("course");
                    for (int i = 0; i < courseArray.size(); i++) {
                        String courseName = (String) courseArray.get(i);

                        Course course = courses.stream().filter(e -> e.getName().equals(courseName))
                                .collect(Collectors.toList()).get(0);

                        student.addCourse(course);// مش عارف اذا راح تزبط

                    }
                }
                if (studentObj.get("gread") instanceof JSONArray) {
                    ArrayList courseArray = (ArrayList) studentObj.get("gread");
                    for (int i = 0; i < courseArray.size(); i++) {
                        double grade = Double.parseDouble((String) courseArray.get(i));
                        student.setGrade(courses.get(i), grade);

                    }
                }

                students.add(student);

                // System.out.println( "student info"+student.StudentInfo());
                // System.out.println("student course"+student.getCourse());
                // System.out.println("student GPA"+student.GPA());
                // System.out.println();
                // System.out.println();
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
   Faculty faculty; Staff staff ; Semester semester;

    public ArrayList<Course> readCourse() {// بترجع و بتجيب اريي الكورسات
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(path2));
            // System.out.println("course:");
            for (Object object : a) {
                JSONObject studentObj = (JSONObject) object;
     
                String name = (String) studentObj.get("name");
                String shortcut = (String) studentObj.get("shortcut");
                int credits = ((Long) studentObj.get("credits")).intValue();

                String staffName = (String) studentObj.get("staff");
                String semesterName = (String) studentObj.get("semester");
                String facultyName = (String) studentObj.get("faculty");
                // Staff staff = new Staff(staffName, "", "0");
                // Faculty faculty = new Faculty();
                // faculty.setName(facultyName);
                // System.out.println("\n\n\n"+Semesters);findFirst().orElse(null);
                Optional<Semester> optionalSemester = Semesters.stream()
                .filter(e -> e.getName().equals(semesterName))
                .findFirst();
        
        Optional<Staff> optionalStaff = staffs.stream()
                .filter(e -> e.getName().equals(staffName))
                .findFirst();
        
        Optional<Faculty> optionalFaculty = facultys.stream()
                .filter(e -> e.getName().equals(facultyName))
                .findFirst();
        if (optionalSemester.isPresent() ) {
             semester = optionalSemester.get();}else {
 System.out.println("if you want to add a Semester use the setSemester method");        }
        if(optionalStaff.isPresent() ){
          staff = optionalStaff.get();}else {
            System.out.println("if you want to add a Staff use the setStaff method");
        }
        if(optionalFaculty.isPresent()){
            faculty = optionalFaculty.get();}else {
            System.out.println("if you want to add a Faculty use the setFaculty method");
        }
         Course course = new Course(name, shortcut, credits, staff, semester, faculty);
         courses.add(course);
        // System.out.println(course.toString() + "\n\n");
      
        
               
                
            }System.out.println(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public ArrayList<Semester> readSemester() {// بترجع و بتجيب اريي السيميستر
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(path3));
            // System.out.println("Semester");
            for (Object object : a) {
                JSONObject SemesterObj = (JSONObject) object;

                String name = (String) SemesterObj.get("name");
                Optional<Object> yearOptional = Optional.ofNullable(SemesterObj.get("year"));

                int year = yearOptional.map(value -> ((Long) value).intValue()).orElse(0);
                LocalDate startDate = LocalDate.parse(((String) SemesterObj.get("startDate")));
                LocalDate endDate = LocalDate.parse((String) SemesterObj.get("endDate"));

                Semester semester = new Semester(name, year, startDate, endDate);

                Semesters.add(semester);

                // System.out.println(semester.toString());
            }System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Semesters;
    }
    public ArrayList<Faculty> readFaculty() {// بترجع و بتجيب اريي السيميستر
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(pathFaculty));
            // System.out.println("faculty");
            for (Object object : a) {
                JSONObject SemesterObj = (JSONObject) object;

                String name = (String) SemesterObj.get("name");
                             String contact = (String) SemesterObj.get("contact");

                Faculty faculty = new Faculty(name, contact);

                facultys.add(faculty);

                // System.out.println(faculty.toString());
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facultys;
    }
    public ArrayList<Staff> readStaff() {// بترجع و بتجيب اريي الستاف
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(pathStaff));
            // System.out.println("Staff :");
            for (Object object : a) {
                JSONObject StaffObj = (JSONObject) object;

                String name = (String) StaffObj.get("name");
                String email = (String) StaffObj.get("email");
                String phoneNumber = (String) StaffObj.get("phoneNumber");


                Staff staff = new Staff(name, email, phoneNumber);
                staffs.add(staff);
;
                // System.out.println(staff.toString());
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffs;
    }

    public ArrayList<Semester> getSemesters() {
        return Semesters;
    }

    public void AddSemesters(Semester semester) {
        Semesters.add(semester);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void AddCourses(Course course) {
        this.courses.add(course);
    }

    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public void AddStaff(Staff staff) {
        this.staffs.add(staff);
    }

    public ArrayList<Faculty> getFacultys() {
        return facultys;
    }

    public void AddFacultys(Faculty faculty) {
        this.facultys.add(faculty); 
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void AddStudents(Student student) {
        this.students.add(student);
    }

    
  




    
}
