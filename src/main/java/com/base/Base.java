package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_201 = 201;

    public Properties prop;

    public Base() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("/Users/nishant.dwivedi/IdeaProjects/RestAPI/src/main/java/com/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
