package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

    public Map<String,String > dataKeyMap(String name, String email, String gender, String status){
        Map<String ,String > dataKeyMap=new HashMap<>();
        dataKeyMap.put("name",name);
        dataKeyMap.put("email",email);
        dataKeyMap.put("gender",gender);
        dataKeyMap.put("status",status);
        return dataKeyMap;
    }
    public Map<String,Object> expectedDataMap(Object meta, Map data){
        Map<String ,Object> expectedDataMap =new HashMap<>();
        expectedDataMap.put("meta",meta);
        expectedDataMap.put("data", data);
        return expectedDataMap;
    }


}
