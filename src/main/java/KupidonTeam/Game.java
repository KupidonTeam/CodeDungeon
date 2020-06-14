package KupidonTeam;


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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) {
//
//        Connection server = Connection.getConnection();
//        Application.launch(LoginWrapper.class, args);


        String str = " {\"attacks\": {\n" +
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
                "    }}";
        JSONObject jsonObject = (JSONObject) new JSONObject(str).get("attacks");
        Set<Integer> skills = new HashSet<>();
        Stream.of(jsonObject.keySet().toArray())
                .forEach(el -> skills.add(Integer.parseInt((String) el)));

        System.out.println(skills);
    }
}
