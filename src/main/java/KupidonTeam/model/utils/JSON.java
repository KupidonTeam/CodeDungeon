package KupidonTeam.model.utils;

import KupidonTeam.model.animals.Animal;
import KupidonTeam.model.characters.Stats;
import KupidonTeam.model.characters.enemies.Enemy;
import KupidonTeam.model.characters.skills.Skill;
import KupidonTeam.model.items.Armor;
import KupidonTeam.model.items.Food;
import KupidonTeam.model.items.Weapon;
import KupidonTeam.model.locations.Dungeon;
import KupidonTeam.model.login.SignLogic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class JSON {
    public static void deserialize(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String key = jsonObject.getString("key");
    }

    //Send a simple message to chat
    public static String message(String msg) {
        return String.format("{\"action\":\"sendChatMessage\",\"data\":{\"message\": \"%s\"}}", msg);
    }

    public static String inboxMessage(JSONObject data) {
        return String.format("%s : %s\n", data.getString("from"), data.getString("message"));
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

    //<--! Методы для парсинга и содания персонажа --!>
    public static List<Skill> skills(JSONObject data) {
        List<Skill> skills = new LinkedList<>();

        JSONObject jsonObject = data.getJSONObject("attacks");
        if (jsonObject.toString().equalsIgnoreCase("{}")) {

            return new LinkedList<>();
        }
        jsonObject.keySet().forEach(el -> {
            JSONObject skill = jsonObject.getJSONObject(el);

            skills.add(
                    new Skill(Integer.parseInt(el),
                            skill.getString("attack_name"),
                            skill.getInt("attack_lvl"),
                            skill.getString("attack_type"),
                            skill.getInt("count_of_random"),
                            skill.getInt("attack_cooldown"),
                            skill.getInt("random_diapason"),
                            skill.getString("attack_effect")
                    ));
        });

        return skills;
    }

    public static List<Food> foods(JSONObject data) {
        List<Food> foods = new LinkedList<>();

        JSONObject jsonObject = data.getJSONObject("food");
        jsonObject.keySet().forEach(el -> {

            JSONObject food = jsonObject.getJSONObject(el);
            foods.add(new Food(Integer.parseInt(el),
                    food.getString("food_name"),
                    food.getDouble("food_price")
            ));
        });

        return foods;
    }

    public static List<Weapon> weapons(JSONObject data) {
        List<Weapon> weapons = new LinkedList<>();

        JSONObject jsonObject = data.getJSONObject("weapons");
        jsonObject.keySet().forEach(el -> {

            JSONObject weapon = jsonObject.getJSONObject(el);
            weapons.add(new Weapon(Integer.parseInt(el),
                    weapon.getString("weapon_name"),
                    weapon.getDouble("weapon_price"),
                    weapon.getInt("weapon_damage_min"),
                    weapon.getInt("weapon_damage_max"),
                    weapon.getDouble("weapon_weight")
            ));
        });

        return weapons;
    }

    public static List<Animal> animals(JSONObject data) {
        List<Animal> animals = new LinkedList<>();

        JSONObject jsonObject = data.getJSONObject("animals");
        jsonObject.keySet().forEach(el -> {

            JSONObject animal = jsonObject.getJSONObject(el);
            animals.add(new Animal(Integer.parseInt(el),
                    animal.getString("animal_name"),
                    animal.getDouble("animal_price"),
                    animal.getDouble("animal_speed"),
                    animal.getInt("animal_capacity")
            ));
        });

        return animals;
    }

    public static List<Armor> armor(JSONObject data) {
        List<Armor> armors = new LinkedList<>();

        JSONObject jsonObject = data.getJSONObject("armor");
        jsonObject.keySet().forEach(el -> {

            JSONObject armor = jsonObject.getJSONObject(el);
            armors.add(new Armor(Integer.parseInt(el),
                    armor.getString("armor_name"),
                    armor.getDouble("armor_weight"),
                    armor.getDouble("armor_price")
            ));
        });

        return armors;
    }

    public static Stats stats(JSONObject stats) {
        try {
            return new Stats(
                    stats.getInt("armor_class"),
                    stats.getInt("hits"),
                    stats.getInt("speed"),
                    stats.getInt("strength"),
                    stats.getInt("dexterity"),
                    stats.getInt("intelligence"),
                    stats.getInt("wisdom"),
                    stats.getInt("chance"),
                    stats.getInt("constitution")
            );
        } catch (JSONException ex) {
            ex.printStackTrace();
            SignLogic.getSignLogic().closeAll();
        }

        return null;
    }

    public static String getDungeonSkeleton() {
        return "{\"action\":\"getDungeonSkeleton\"}";
    }

    public static Enemy mob(JSONObject jsonMob, int id) {
        return new Enemy(id,
                jsonMob.getString("name"),
                jsonMob.getString("desc"),
                jsonMob.getInt("mob_level"),
                JSON.stats(jsonMob.getJSONObject("stats")),
                JSON.skills(jsonMob),
                jsonMob.getJSONObject("vulnerabilities").toString()
        );
    }

    public static List<Dungeon> dungeons(JSONObject data) {
        List<Dungeon> dungeonList = new ArrayList<>();

        JSONArray roomsIndexes = data.getJSONObject("dungeonSkeleton").getJSONArray("rooms");
        JSONArray routes = data.getJSONObject("dungeonSkeleton").getJSONArray("routes");
        int mas[][] = new int[routes.length()][2];
        for (int i = 0; i < routes.length(); i++) {
            JSONArray temp = (JSONArray) routes.get(i);
            mas[i][0] = (int) temp.get(0);
            mas[i][1] = (int) temp.get(1);
        }
        Container.setRoutes(mas);

        System.out.println("=================================>\n");

        Map<Integer, Set<Integer>> directions = new HashMap<>();
        roomsIndexes.forEach(el -> directions.put((Integer) el, new HashSet<>()));

        for (int i = 0; i < routes.length(); i++) {
            JSONArray temp = (JSONArray) routes.get(i);
            directions.get(temp.get(0)).add((Integer) temp.get(1));
            directions.get(temp.get(1)).add((Integer) temp.get(0));
        }

        System.out.println("Map with routes :");
        directions.keySet().forEach(el -> directions.get(el).forEach(el2 -> System.out.println(el + " = " + el2)));
        System.out.println("=============================================");
        System.out.println(directions.get(0));
        roomsIndexes.forEach(el -> {
            List<Enemy> enemies = new LinkedList<>();
            JSONObject room = data.getJSONObject("dungeonSkeleton")
                    .getJSONObject("mobs").getJSONObject(String.valueOf((Integer) roomsIndexes.get((Integer) el)));

            room.keySet().forEach(elem ->
                    enemies.add(mob(room.getJSONObject(elem), Integer.parseInt(elem))
                    ));

            dungeonList.add(new Dungeon((Integer) el,
                    new LinkedList<>(directions.get((Integer) el)),
                    "unnamed",
                    "no desc",
                    enemies
            ));
        });

        dungeonList.forEach(el -> {
            System.out.println(el.getAvailableDirections());
            if (el.getAvailableDirections().isEmpty()) {
                System.out.println("room #" + el.getRoomId() + " = Empty");
                if (el.getRoomId() != 0) {
                    System.out.println("add 0");
                    el.getAvailableDirections().add(0);
                    dungeonList.get(0).getAvailableDirections().add(el.getRoomId());
                } else {
                    el.getAvailableDirections().add(1);
                    dungeonList.get(1).getAvailableDirections().add(el.getRoomId());
                }
            }
        });

        System.out.println("result = ");
        dungeonList.forEach(el -> System.out.println(el.getAvailableDirections()));

        return dungeonList;
    }

    public static String startBattle(int peekedSkillId, Enemy enemy) {
        return String.format("{\n" +
                        "  \"action\": \"startBattle\",\n" +
                        "  \"data\": {\n" +
                        "    \"player_attack_id\": %d,\n" +
                        "    \"mob\": {%s}" +
                        "}}",
                peekedSkillId, enemy.toString());
    }

    //удаляем перенос на новую строку и пробелы
    public static String normalize(String json) {
        return json.replaceAll("\n| ", "");
    }

    public static String registration(String username, String password, int classId, int raceId, String avatar) {
        return String.format("{" +
                "\"action\":\"playerRegistration\"," +
                "\"data\":{\"player_name\": \"%s\"," +
                "\"player_password\": \"%s\"," +
                "\"class_id\": %d, " +
                "\"race_id\": %d," +
                "\"avatar\" : \"%s\"}}", username, password, classId, raceId, avatar);
    }

    public static String getLoot() {
        return "{\"action\":\"getLoot\"}";
    }
}
