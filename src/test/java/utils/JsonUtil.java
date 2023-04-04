package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

    //1.Method: This method will convert Json Data to Java Object (De-Serialization)
    public static <T> T convertJsonToJava(String json, Class<T> cls){ //Generic Method

        T javaResult = null;

        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.out.println("Json could not be converted to Java Object " + e.getMessage());
        }

        return javaResult;
    }

    //2.Method: This method will convert Java Object to Json Data (Serialization)
    public static String convertJavaToJson(Object obj){

        String jsonResult = null;

        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java Object could not be converted to Json " + e.getMessage());
        }

        return jsonResult;
    }

}
