package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtil {

    private static final String File_Path = "src/test/resources/TestData/";

    public static String getJsonData(String FileName, String field) throws FileNotFoundException {

        FileReader reader = new FileReader(File_Path + FileName + ".json");
        JsonElement jsonElement = JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(field).getAsString();
    }

    public static String getPropertyValue(String FileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(File_Path + FileName + ".properties"));
        return properties.getProperty(key);
    }

}
