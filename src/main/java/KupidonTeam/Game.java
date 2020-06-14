package KupidonTeam;


import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.gui.LoginWrapper;
import KupidonTeam.server.Connection;
import javafx.application.Application;
import javafx.scene.control.Alert;
import lombok.SneakyThrows;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) {
//
//        Connection server = Connection.getConnection();
//        Application.launch(LoginWrapper.class, args);


        String str = "{\n" +
                "  \"action\": \"playerAuthorization\",\n" +
                "  \"code\": 203,\n" +
                "  \"code_desc\": \"successfully authorized\",\n" +
                "  \"data\": {\n" +
                "    \"race\": \"Human\",\n" +
                "    \"class_name\": \"Mage\",\n" +
                "    \"armor_class\": null,\n" +
                "    \"hits\": null,\n" +
                "    \"speed\": null,\n" +
                "    \"strength\": null,\n" +
                "    \"dexterity\": null,\n" +
                "    \"intelligence\": null,\n" +
                "    \"wisdom\": null,\n" +
                "    \"chance\": null,\n" +
                "    \"constitution\": null,\n" +
                "    \"level\": 1,\n" +
                "    \"experience\": 0,\n" +
                "    \"player_id\": 8,\n" +
                "    \"player_name\": \"test6\",\n" +
                "    \"class_id\": 1,\n" +
                "    \"race_id\": 1,\n" +
                "    \"food\": {\n" +
                "      \"1\": {\n" +
                "        \"food_name\": \"begg food\",\n" +
                "        \"food_price\": 7.0\n" +
                "      }\n" +
                "    },\n" +
                "    \"armor\": {\n" +
                "      \"1\": {\n" +
                "        \"armor_name\": \"Improved Leather\",\n" +
                "        \"armor_weight\": 12,\n" +
                "        \"armor_price\": 450.0\n" +
                "      }\n" +
                "    },\n" +
                "    \"weapons\": {\n" +
                "      \"1\": {\n" +
                "        \"weapon_name\": \"Staff Weapon\",\n" +
                "        \"weapon_price\": 20.0,\n" +
                "        \"damage_min\": 1,\n" +
                "        \"damage_max\": 6,\n" +
                "        \"weapon_weight\": 4.0\n" +
                "      }\n" +
                "    },\n" +
                "    \"animals\": {\n" +
                "      \"1\": {\n" +
                "        \"animal_name\": \"Riding Horse\",\n" +
                "        \"animal_price\": 7500.0,\n" +
                "        \"animal_speed\": 60.0,\n" +
                "        \"animal_capacity\": 480\n" +
                "      }\n" +
                "    },\n" +
                "    \"vulnerabilities\": {},\n" +
                "    \"attacks\": {\n" +
                "      \"4\": {\n" +
                "        \"attack_name\": \"Thor\",\n" +
                "        \"lvl\": 1,\n" +
                "        \"type_attack\": \"Long Range\",\n" +
                "        \"count_of_random\": 2,\n" +
                "        \"cooldown\": 5,\n" +
                "        \"random_diapason\": 8,\n" +
                "        \"effect\": \"embodiment\"\n" +
                "      },\n" +
                "      \"7\": {\n" +
                "        \"attack_name\": \"A ray of cold\",\n" +
                "        \"lvl\": 0,\n" +
                "        \"type_attack\": \"Long Range\",\n" +
                "        \"count_of_random\": 1,\n" +
                "        \"cooldown\": 5,\n" +
                "        \"random_diapason\": 8,\n" +
                "        \"effect\": \"embodiment\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        JSONObject data = new JSONObject().getJSONObject("data");
        List<Skill> skills = new LinkedList<>();


//        JSONObject jsonObject = (JSONObject) new JSONObject(str).get("attacks");
//        Set<Integer> skills = new HashSet<>();
//        Stream.of(jsonObject.keySet().toArray())
//                .forEach(el -> skills.add(Integer.parseInt((String) el)));
//
//        System.out.println(skills);
    }
}
