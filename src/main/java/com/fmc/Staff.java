package com.fmc;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.JSONWriter;

public class Staff extends Student {
    ArrayList<Course> staffCourseArray = new ArrayList<>();

    private String name;
    private String email;
    private String phoneNumber;

    public Staff(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public ArrayList<Course> getCourse(String staffName) {/// جاهزة
        ArrayList<Course> st = staffCourse.get(this);
        return st;
        // List<Course>getCourses=
        // st.stream().filter(e->e.getStaff().equals(this)).collect(Collectors.toList());
        // (ArrayList<Course>) getCourses;
    }

    // اعطي كورس للمعلم
    public void setCourse(Course course) {
        ArrayList<Course> temp = staffCourse.get(this);
        temp.add(course);
        staffCourse.put(this, temp);
    }
    // public void assignGrade(Student student,Course course,Double grade ) throws
    // InterruptedException{// لازم اتأكد انو المادة موجودة جاهزة

    // int testCours=(int)(
    // Stream.of(staffCourse).filter(e->e.equals(course)).count());
    // ArrayList<Student>cour=course.getStudent();
    // int test =(int) cour.stream().filter(e->e.equals(student)).count();
    // if(test!=0&&testCours!=0){
    // ArrayBlockingQueue<Student> gpas=new ArrayBlockingQueue<>(2);
    // student.setGrade(course, grade) ;
    // gpas.put(student);}
    // else{
    // System.out.println("not exist");
    // }

    // }
    // اجيب كورسات المعلم
    public ArrayList<Course> getCourse() {
        return staffCourseArray;
    }

    public void addCourse(Course course1) {
        staffCourseArray.add(course1);
    }

    @Override
    public String toString() {
        return "  name=" + name + ", email=" + email + ", phoneNumber="
                + phoneNumber + " \n staffCourse" + this.getCourse();
    }

    public JsonNode jsonify_object() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{")
                .append("\"name\": \"").append(escapeJsonString(name))
                // .append("\",")
                // ToDo: Write the other attibutes for this class...
                // See Course.java as an example.
                .append("\"}");
        

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

            File file = new File("assets\\data\\staff.json");
            JSONWriter jfa = new JSONWriter();

            jfa.appendToArray(file, jsonify_object());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}