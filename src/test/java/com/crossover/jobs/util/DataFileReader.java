package com.crossover.jobs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataFileReader {
    private static final Logger logger = LoggerFactory.getLogger(DataFileReader.class);

    public WebDriver driver;

    //this method is for read data from property file (e.g file.properties)

    public static String readData(String key) {
        String value = "";
        try {

            Properties properties = new Properties();
            File file = new File(System.getProperty("user.dir") + "/src/test/resources/config/file.properties");

            if (file.exists()) {
                properties.load(new FileInputStream(file));
                value = properties.getProperty(key);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return value;
    }

    //this method is for read data with file name from property file (e.g file.properties)

    public static String readData(String key, String fileName) {
        String value = "";
        try {

            Properties properties = new Properties();
            File file = new File(System.getProperty("user.dir") + "/src/test/resources/config/file.properties");

            if (file.exists()) {
                properties.load(new FileInputStream(file));
                value = properties.getProperty(key);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return value;
    }

    //this method is for write data from property file (e.g file.properties)

    public static void writeData(String key, String value) {
        FileOutputStream output = null;

        try {
            Properties prop = new Properties();
            File fis = new File(System.getProperty("user.dir") + "/src/test/resources/config/file.properties");

            FileInputStream fileName = new FileInputStream(fis);

            prop.load(fileName);

            // set the properties value
            prop.setProperty(key, value);
            fileName.close();
            output = new FileOutputStream(fis);

            // save properties to project root folder
            prop.store(output, "");

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }

}