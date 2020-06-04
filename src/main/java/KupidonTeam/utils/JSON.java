package KupidonTeam.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class JSON {
    public static void deserialize(String json) {
        JSONObject jsonObject = new JSONObject(json);
        System.out.println("hey" + jsonObject);
        String key = jsonObject.getString("key");
        System.out.println(key);
    }

    //Send a simple message to chat
    public static String message(String msg, String playerName) {
        return normalize(String.format("{\"action\":\"sendChatMessage\"," +
                "\"data\":{" +
                "\"player_name\": \"%s\"," +
                "\"message\": \"%s\"}}", playerName, msg));
    }

    //TODO в json нет данных для отправки 'from', есть только 'to'
    public static String directMessage(String msg, String from, String to) {
        return normalize(String.format("{\"action\":\"sendPrivateMessage\"," +
                "\"data\":{" +
                "\"to\": \"%s\"," +
                "\"message\": \"%s\"}}", to, msg));
    }

    public static String login(String username, String password) {
        return normalize(String.format("{\"action\":\"playerAuthorization\"," +
                "\"data\":{" +
                "\"player_name\": \"%s\"," +
                "\"player_password\": \"%s\"}}\n", username, password));
    }

    //TODO json еще не полностью готов
    public static String registration() {
        return null;
    }


    //удаляем перенос на новую строку и пробелы
    public static String normalize(String json) {
        return json.replaceAll("\n| ", "");
    }

}
