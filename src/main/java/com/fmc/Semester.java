package com.fmc;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.JSONWriter;

public class Semester extends Course {

    ArrayList<Course> c = new ArrayList<>();// c يعني semesterCourseArray
    private String name;
    private int year;
    private LocalDate start;
    private LocalDate end;

    public Semester() {
    }

    public Semester(String name,int year, LocalDate start, LocalDate end) {
        super();
        this.year = year;
        this.start = start;
        this.end = end;
        this.name=name;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // public LocalDate getStart() {
    // return start;
    // }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    // public LocalDate getEnd() {
    // return end;
    // }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
// اجيب كورسات الفثل 
    public ArrayList<Course> getCourse() {
        if (semesterCourse.get(this)==null) 
        System.out.println("this semester have not  any course");
       return semesterCourse.get(this);

    }

// احطله كورس
    public void createNewCourseinSemester(String name, String shortcut, int credits, Faculty faculty) {

        Course course = new Course(name, shortcut, credits, this, faculty);
        this.c.add(course);
        this.semesterCourse.put(this, c);
     
    }
// احذف كورس من هاد الفصل
    public void removeCourse(Course course) {
        this.c.remove(course);

    }
// احذف كورس باستخدام اسم الكورس
    public void removeCourse(String Namecourse) {

        List<String> removes = this.c.stream().map(e -> e.getName().toLowerCase())
                .filter(e -> e.equals(Namecourse.toLowerCase())).collect(Collectors.toList());
        this.c.remove(removes);
    }
// اطبع معلومات الفصل
    @Override
    public String toString() {
        
            return "Semester [c=" + c + ", name=" + name + ", year=" + year + ", start=" + start + ", end=" + end + "]";
            
        }

        
    public JsonNode jsonify_object() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{")
                .append("\"name\": \"").append(escapeJsonString(name))
                // .append("\",")
                // ToDo: Write the other attibutes for this class...
                // See Course.java as an example.
                .append("\"}")
                ;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(stringBuilder.toString());
        } catch (Exception e) {
            throw e;
        }
    }

    private String escapeJsonString(String input) {
        // Replace special characters with their escaped versions
        return input
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public void append_to_json_file() {
        try {

            File file = new File("assets\\data\\Semester.json");
            JSONWriter jfa = new JSONWriter();

            jfa.appendToArray(file, jsonify_object());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}