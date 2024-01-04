package com.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JSONWriter {
    private final ObjectMapper jsonMapper;

    public JSONWriter() {
        this.jsonMapper = JsonMapper.builder().build();
    }

    public void appendToArray(File jsonFile, Object value) throws IOException {
        Objects.requireNonNull(jsonFile);
        Objects.requireNonNull(value);
        if (jsonFile.isDirectory()) {
            throw new IllegalArgumentException("File can not be a directory!");
        }

        JsonNode node = readArrayOrCreateNew(jsonFile);
        if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            System.out.println("beo beo, here: ");
            System.out.println(value);
            array.addPOJO(value);
        } else {
            ArrayNode rootArray = jsonMapper.createArrayNode();
            rootArray.add(node);
            rootArray.addPOJO(value);
            node = rootArray;
        }
        jsonMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, node);
        // jsonMapper.writeValue(jsonFile, node);
    }

    private JsonNode readArrayOrCreateNew(File jsonFile) throws IOException {
        if (jsonFile.exists() && jsonFile.length() > 0) {
            return jsonMapper.readTree(jsonFile);
        }

        return jsonMapper.createArrayNode();
    }
}