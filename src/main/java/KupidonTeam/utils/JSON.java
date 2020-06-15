package KupidonTeam.utils;

import KupidonTeam.animals.Animal;
import KupidonTeam.characters.classes.Stats;
import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.items.Armor;
import KupidonTeam.items.Food;
import KupidonTeam.items.Weapon;
import org.json.JSONObject;


import java.util.LinkedList;
import java.util.List;

public class JSON {
    public static void deserialize(String json) {
        JSONObject jsonObject = new JSONObject(json);
        System.out.println("hey" + jsonObject);
        String key = jsonObject.getString("key");
        System.out.println(key);

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

    //TODO json еще не полностью готов
    public static String registration() {
        return null;
    }

    //<--! Методы для парсинга и содания персонажа --!>
    public static List<Skill> skills(JSONObject data) {
        List<Skill> skills = new LinkedList<>();
        JSONObject jsonObject = data.getJSONObject("attacks");
        jsonObject.keySet().forEach(el -> {
            JSONObject skill = jsonObject.getJSONObject(el);
            skills.add(
                    new Skill(Integer.parseInt(el),
                            skill.getString("attack_name"),
                            skill.getInt("lvl"),
                            skill.getString("type_attack"),
                            skill.getInt("count_of_random"),
                            skill.getInt("cooldown"),
                            skill.getInt("random_diapason"),
                            skill.getString("effect")
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
                    weapon.getInt("damage_min"),
                    weapon.getInt("damage_max"),
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

    public static Stats stats(JSONObject data) {
        return new Stats(
                data.getString("race"),
                data.getInt("armor_class"),
                data.getInt("hits"),
                data.getInt("speed"),
                data.getInt("strength"),
                data.getInt("dexterity"),
                data.getInt("intelligence"),
                data.getInt("wisdom"),
                data.getInt("chance"),
                data.getInt("constitution")
        );
    }

    //<--!  Конец   --!>
    //удаляем перенос на новую строку и пробелы
    public static String normalize(String json) {
        return json.replaceAll("\n| ", "");
    }

}
