package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiReader {


    public static Properties getProperty() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/connection.properties"));
        Properties properties = new Properties();
        properties.load(reader);
        return properties;
    }
}
