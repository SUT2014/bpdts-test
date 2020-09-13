//Properties Loader

package com.github.SUT2014.bpdts.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoad {
    private static String BASE_URL;
    private static String NUM_OF_USERS;

    public static boolean loadProp() {
        InputStream inStream = null;
        try {
            Properties prop = new Properties();
            inStream = PropertiesLoad.class.getClassLoader().getResourceAsStream("config.properties");

            if (inStream == null) {
                System.out.println("Unable to find config.properties");
                return false;
            }
            prop.load(inStream);
            BASE_URL = prop.getProperty("BASE_URL");
            NUM_OF_USERS = prop.getProperty("NUM_OF_USERS");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                if (inStream != null)
                    inStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return true;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getNumOfUsers() {
        return NUM_OF_USERS;
    }
}