package com.fmc;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.JSONWriter;

public class Faculty extends user {

    // intfatmaaagit
    private String name;
    private String contact;
    ArrayList<Course> facultyCourseArray = new ArrayList<>();/////////////

    public Faculty(String name, String contact) {
        super();
        this.name = name;
        this.contact = contact;

    }

    public Faculty() {
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ArrayList<Course> getCourse() {
        return facultyCourseArray;
    }

    @Override
    public String toString() {
        return "Faculty [name=" + name + ", contact=" + contact + ", facultyCourseArray=" + getCourse() + "]";
    }

    public JsonNode jsonify_object() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();   ObjectMapper objectMapper = new ObjectMapper();
        if(!user.getFacultyCourse().keySet().stream().anyMatch(e->e.getName().equals(name))){
        stringBuilder.append("{")
                .append("\"name\": \"").append(escapeJsonString(name))
                // .append("\",")
                // ToDo: Write the other attibutes for this class...
                // See Course.java as an example.
                .append("\"}");

        try {
         
            return objectMapper.readTree(stringBuilder.toString());
        } catch (Exception e) {
            throw e;
        }}else{ return objectMapper.readTree("");}
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
if (!jsonify_object().isNull() ||jsonify_object().isTextual() && jsonify_object().textValue().isEmpty()){
            File file = new File("assets\\data\\faculty.json");
            JSONWriter jfa = new JSONWriter();

            jfa.appendToArray(file, jsonify_object());
}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}