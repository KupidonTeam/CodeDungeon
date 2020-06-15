package KupidonTeam;


import KupidonTeam.characters.classes.Stats;
import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.gui.LoginWrapper;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import javafx.application.Application;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) {
//
//        Connection server = Connection.getConnection();
//        Application.launch(LoginWrapper.class, args);

        String in = "{\n" +
                "    \"dungeonSkeleton\": {\n" +
                "      \"mobs\": {\n" +
                "        \"0\": {\n" +
                "          \"0\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Philistin\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          }\n" +
                "        },\n" +
                "        \"1\": {\n" +
                "          \"0\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Black Spider\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"1\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Philistin\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          }\n" +
                "        },\n" +
                "        \"2\": {\n" +
                "          \"0\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Big Beast\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Giant Spider\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"1\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Philistin\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          }\n" +
                "        },\n" +
                "        \"3\": {\n" +
                "          \"0\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Evil Mage\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"1\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Red-Marked \",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          }\n" +
                "        },\n" +
                "        \"4\": {\n" +
                "          \"0\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Beast\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Wolf\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"1\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Aberration\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Spectator\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          }\n" +
                "        },\n" +
                "        \"5\": {\n" +
                "          \"0\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Black Spider\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"1\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Big Slime\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Golden Jelly\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"2\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Red-Marked \",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          }\n" +
                "        },\n" +
                "        \"6\": {\n" +
                "          \"0\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Humanoid\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Evil Mage\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"1\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Big Giant\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Ogre\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          },\n" +
                "          \"2\": {\n" +
                "            \"attacks\": {},\n" +
                "            \"desc\": \"Medium Human\",\n" +
                "            \"inventory\": {},\n" +
                "            \"mob_level\": 1,\n" +
                "            \"name\": \"Cultist\",\n" +
                "            \"stats\": {\n" +
                "              \"armor_class\": 2,\n" +
                "              \"chance\": 0.0,\n" +
                "              \"constitution\": 3.0,\n" +
                "              \"dexterity\": 8.0,\n" +
                "              \"hits\": 3,\n" +
                "              \"intelligence\": 6.0,\n" +
                "              \"speed\": 4.0,\n" +
                "              \"strength\": 5.0,\n" +
                "              \"wisdom\": 9.0\n" +
                "            },\n" +
                "            \"vulnerabilities\": {}\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"rooms\": [0, 1, 2, 3, 4, 5, 6],\n" +
                "      \"routes\": [\n" +
                "        [0, 1], [0, 4], [1, 2], [1, 5], [2, 3], [2, 5], [2, 6], [3, 5], [5, 6]\n" +
                "      ]\n" +
                "    }\n" +
                "  }";
        JSONObject data = new JSONObject(in);
        List<Dungeon> dungeonList = new ArrayList<>();

        JSONArray roomsIndexes = data.getJSONObject("dungeonSkeleton").getJSONArray("rooms");
        JSONArray routes = data.getJSONObject("dungeonSkeleton").getJSONArray("routes");
        roomsIndexes.forEach(el -> {
            List<Integer> availableDir = new LinkedList<>();
            for (int i = 0; i < routes.length(); i++) {
                JSONArray temp = (JSONArray) routes.get(i);
                if (temp.get(0).equals(el)) {
                    System.out.println("Room : " + el + " Dir = " + temp.get(1));

                    availableDir.add((Integer) temp.get(1));
                }
            }
            System.out.println("MOBS");
            List<Enemy> enemies = new LinkedList<>();
            System.out.println("Room #" + el);
            JSONObject room = data.getJSONObject("dungeonSkeleton")
                    .getJSONObject("mobs").getJSONObject(String.valueOf((Integer) roomsIndexes.get((Integer) el)));

            room.keySet().forEach(elem -> {
//                System.out.println("ID = " + elem + "\n" + room.getJSONObject(elem));
                System.out.println("CREATE ENEMY :");
                enemies.add(mob(room.getJSONObject(elem), Integer.parseInt(elem)));
            });
            Integer[] tempArr = new Integer[availableDir.size()];
            availableDir.toArray(tempArr);
            dungeonList.add(new Dungeon((Integer) el,
                    tempArr,
                    "unnamed",
                    "no desc",
                    enemies
            ));
        });

        System.out.println("========================================");
        System.out.println("RESULT : ");
        for (int i = 0; i < dungeonList.size(); i++) {
            System.out.println("NUMBER #" + i);
            System.out.println(dungeonList.get(i).toString());
            System.out.println("\n\n\n");
        }

//        roomsIndexes.forEach(el->{
//            dungeonList.add(new Dungeon(el.toString(),))
//            Set<String> mobsInRoom = rooms.getJSONObject(el.toString()).keySet();
//
//        });


    }

    static Enemy mob(JSONObject jsonMob, int id) {
        return new Enemy(id,
                jsonMob.getString("name"),
                jsonMob.getString("desc"),
                jsonMob.getInt("mob_level"),
                JSON.stats(jsonMob.getJSONObject("stats"))
        );
    }

}