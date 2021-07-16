package com.terminalio.sitetracker.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= System.getProperty("user.dir")+"/src/test/resources/configs/Configuration.properties";

    public ConfigFileReader(){
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

    public String getBrowser(){
        String driverType = properties.getProperty("driverType");
        if(driverType!= null) return driverType;
        else throw new RuntimeException("driverType not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getEnvironment() {
        String environmentType = properties.getProperty("environmentType");
        if(environmentType != null) return environmentType;
        else throw new RuntimeException("environmentType not specified in the Configuration.properties file.");
    }

    public String getUrl(String envType) {
        String environmentType = properties.getProperty("baseUrl"+envType);
        if(environmentType != null) return environmentType;
        else throw new RuntimeException("environmentType not specified in the Configuration.properties file.");
    }
}