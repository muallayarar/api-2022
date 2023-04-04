package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public Map<String,String > bookingDatesSetUp(String checkin,String checkout) {
        Map<String,String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2020-09-09");
        bookingDatesMap.put("checkout", "2020-09-21");
        return bookingDatesMap;
    }
    public Map<String,Object > expectedDataSetUp(String firstname, String lastname, int totalprice, boolean depositpaid, Map<String,String>bookingdates){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Selim");
        expectedData.put("lastname", "Ak");
        expectedData.put("totalprice", 11111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);
        return expectedData;
    }

}
