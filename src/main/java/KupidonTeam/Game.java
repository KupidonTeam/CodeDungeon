package KupidonTeam;


import KupidonTeam.characters.classes.Stats;
import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.gui.LoginWrapper;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;


//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) throws IOException {

        Connection server = Connection.getConnection();
        Application.launch(LoginWrapper.class, args);

//        String in = "";
//        JSONObject data = new JSONObject(in);
//        List<Dungeon> dungeonList = new ArrayList<>();
//
//        JSONArray roomsIndexes = data.getJSONObject("dungeonSkeleton").getJSONArray("rooms");
//        JSONArray routes = data.getJSONObject("dungeonSkeleton").getJSONArray("routes");
//        roomsIndexes.forEach(el -> {
//            List<Integer> availableDir = new LinkedList<>();
//            for (int i = 0; i < routes.length(); i++) {
//                JSONArray temp = (JSONArray) routes.get(i);
//                if (temp.get(0).equals(el)) {
//                    System.out.println("Room : " + el + " Dir = " + temp.get(1));
//
//                    availableDir.add((Integer) temp.get(1));
//                }
//            }
//            System.out.println("MOBS");
//            List<Enemy> enemies = new LinkedList<>();
//            System.out.println("Room #" + el);
//            JSONObject room = data.getJSONObject("dungeonSkeleton")
//                    .getJSONObject("mobs").getJSONObject(String.valueOf((Integer) roomsIndexes.get((Integer) el)));
//
//            room.keySet().forEach(elem -> {
////                System.out.println("ID = " + elem + "\n" + room.getJSONObject(elem));
//                System.out.println("CREATE ENEMY :");
//                enemies.add(mob(room.getJSONObject(elem), Integer.parseInt(elem)));
//            });
//            Integer[] tempArr = new Integer[availableDir.size()];
//            availableDir.toArray(tempArr);
//            dungeonList.add(new Dungeon((Integer) el,
//                    tempArr,
//                    "unnamed",
//                    "no desc",
//                    enemies
//            ));
//        });
//
//        System.out.println("========================================");
//        System.out.println("RESULT : ");
//        for (int i = 0; i < dungeonList.size(); i++) {
//            System.out.println("NUMBER #" + i);
//            System.out.println(dungeonList.get(i).toString());
//            System.out.println("\n\n\n");
//        }
//
////        roomsIndexes.forEach(el->{
////            dungeonList.add(new Dungeon(el.toString(),))
////            Set<String> mobsInRoom = rooms.getJSONObject(el.toString()).keySet();
////
////        });
//
//
//    }
//
//    static Enemy mob(JSONObject jsonMob, int id) {
//        return new Enemy(id,
//                jsonMob.getString("name"),
//                jsonMob.getString("desc"),
//                jsonMob.getInt("mob_level"),
//                JSON.stats(jsonMob.getJSONObject("stats"))
//        );
    }

}