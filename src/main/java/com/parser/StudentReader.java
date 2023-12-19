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

    final String path;

    final String path2;
    final String path3;

    public StudentReader(String path, String path2, String path3) {
        this.path = path;
        this.path2 = path2;
        this.path3 = path3;
    }

    public void read() {// بترجع و بتجيب اريي الطلاب وكمان كورسات الطالب والعلامات
        JSONParser parser = new JSONParser();
        ArrayList<Student> students = new ArrayList<>();
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

                System.out.println(student.StudentInfo());
                System.out.println(student.getCourse());
                System.out.println(student.GPA());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readCourse() {// بترجع و بتجيب اريي الكورسات
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(path2));
            for (Object object : a) {
                JSONObject studentObj = (JSONObject) object;

                String name = (String) studentObj.get("name");
                String shortcut = (String) studentObj.get("shortcut");
                int credits = ((Long) studentObj.get("credits")).intValue();

                String staffName = (String) studentObj.get("staff");
                String semesterName = (String) studentObj.get("semester");
                String facultyName = (String) studentObj.get("faculty");
                Staff staff = new Staff(staffName, "", 0);
                Faculty faculty = new Faculty();
                faculty.setName(facultyName);
                // System.out.println("\n\n\n"+Semesters);findFirst().orElse(null);
                Semester semester = Semesters.stream().filter(e -> e.getName().equals(semesterName))
                        .collect(Collectors.toList()).get(0);
                Course course = new Course(name, shortcut, credits, staff, semester, faculty);
                courses.add(course);

                System.out.println(course.toString() + "\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readSemester() {// بترجع و بتجيب اريي السيميستر
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(path3));
            for (Object object : a) {
                JSONObject SemesterObj = (JSONObject) object;

                String name = (String) SemesterObj.get("name");
                Optional<Object> yearOptional = Optional.ofNullable(SemesterObj.get("year"));

                int year = yearOptional.map(value -> ((Long) value).intValue()).orElse(0);
                LocalDate startDate = LocalDate.parse(((String) SemesterObj.get("startDate")));
                LocalDate endDate = LocalDate.parse((String) SemesterObj.get("endDate"));

                Semester semester = new Semester(name, year, startDate, endDate);

                Semesters.add(semester);

                System.out.println(semester.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
