package com.parser;

import java.io.FileReader;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import com.fmc.Student;

public class StudentReader {

    final String path;

    public StudentReader(String path) {
        this.path = path;
    }

    public void read() {
        JSONParser parser = new JSONParser();
        ArrayList<Student> students = new ArrayList<>();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(path));
            for (Object object : a) {
                JSONObject studentObj = (JSONObject) object;

                String name = (String) studentObj.get("name");
                double gpa = (double) studentObj.get("gpa");
                String contact = (String) studentObj.get("contact");
                int id = ((Long) studentObj.get("id")).intValue();

                Student student = new Student(name, id, contact, gpa);
                students.add(student);
                // System.out.println(student.getName());
                // System.out.println(student.getId());
                // System.out.println(student.getContact());
                System.out.println(student.GPA());
                System.out.println(student.StudentInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
