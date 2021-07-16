package com.terminalio.sitetracker.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestDataFileReader {

    private Properties properties;
    private final String propertyFilePath= System.getProperty("user.dir")+"/src/test/resources/configs/defaultTestData.properties";

    public TestDataFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDefaultUser() {
        String environmentType = properties.getProperty("defaultUser");
        if(environmentType != null) return environmentType;
        else throw new RuntimeException("defaultUser not specified in the defaultTestData.properties file.");
    }

    public String getDefaultPassword() {
        String environmentType = properties.getProperty("defaultPassword");
        if(environmentType != null) return environmentType;
        else throw new RuntimeException("defaultPassword not specified in the defaultTestData.properties file.");
    }

    public String getDefaultName() {
        String environmentType = properties.getProperty("defaultName");
        if(environmentType != null) return environmentType;
        else throw new RuntimeException("defaultName not specified in the defaultTestData.properties file.");
    }

    public String getDefaultLastName() {
        String environmentType = properties.getProperty("defaultLastName");
        if(environmentType != null) return environmentType;
        else throw new RuntimeException("defaultLastName not specified in the defaultTestData.properties file.");
    }

    public String getDefaultZipCode() {
        String environmentType = properties.getProperty("defaultZipCode");
        if(environmentType != null) return environmentType;
        else throw new RuntimeException("defaultZipCode not specified in the defaultTestData.properties file.");
    }
}