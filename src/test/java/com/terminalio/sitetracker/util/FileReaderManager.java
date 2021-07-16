package com.terminalio.sitetracker.util;

public class FileReaderManager {

    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigFileReader configFileReader;
    private static TestDataFileReader testDataFileReader;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance( ) {
        return fileReaderManager;
    }

    public ConfigFileReader getConfigReader() {
        return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
    }
    public TestDataFileReader getTestDataFileReader() {
        return (testDataFileReader == null) ? new TestDataFileReader() : testDataFileReader;
    }
}