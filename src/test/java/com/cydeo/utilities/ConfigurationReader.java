package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {


    //1- Create object from Properties class
    //make it privates so it is not accessible from outside of the class
    //"static" because it should be belcong to class and it should be created and loaded before
    //everything else

    private static Properties properties = new Properties();

    static {

        try {
            //2- open file from java memory through FileInputStream reader
            FileInputStream file = new FileInputStream("configuration.properties");

            //3- Load the file to properties object
            properties.load(file);
            //Not mandatory but better to close the file in the memory
            file.close();

        } catch (IOException e) {
            System.out.println("File not found with given path");
            e.printStackTrace();
        }
    }


    // create utility method to ue the object to read (public)
    //4- read the file through the properties object

    public static String getProperty (String keyword){

        return properties.getProperty(keyword);



    }

}
