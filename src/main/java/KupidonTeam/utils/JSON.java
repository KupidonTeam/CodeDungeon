package KupidonTeam.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class JSON {

    public static String serialize(Object objectToJson, String key){
        ObjectMapper mapper = new ObjectMapper();
        return null;
    }

    /*
    Room :
    {
        dungeon/city : {
                        "id" : "001",
                        "directions" : ["N","S"...],
                        "description"  : "some info",
                        "enemy" : ["id 1","id 2"...],
                        "drop" : ["item id"...]
                        }
    }
     */

    public static void deserialize(String json){
        JSONObject jsonObject = new JSONObject(json);
        System.out.println("hey"+jsonObject);
        String key = jsonObject.getString("key");
        System.out.println(key);
    }
}
