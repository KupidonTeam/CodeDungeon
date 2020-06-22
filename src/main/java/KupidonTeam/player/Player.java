package KupidonTeam.player;

import KupidonTeam.DB.DBConnection;
import KupidonTeam.animals.Animal;
import KupidonTeam.characters.classes.Stats;
import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.items.Armor;
import KupidonTeam.items.Food;
import KupidonTeam.items.Item;
import KupidonTeam.items.Weapon;
import KupidonTeam.locations.Lobby;
import KupidonTeam.locations.Room;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter

public class Player {

    private static Player player;

    private String name;
    private Stats stats;
    private String playerClass;
    private int lvl;
    private int experience;
    private Room location;
    private List<Skill> skills;
    private List<Animal> animals;
    private Image avatarIcon;
    private Inventory inventory;
    private int gold;


    private Player(String name, Stats stats, String playerClass, int lvl, int experience, List<Skill> skills,
                   List<Armor> armors, List<Weapon> weapons, List<Animal> animals) {
        setName(name);
        setStats(stats);
        setPlayerClass(playerClass);
        setSkills(skills);
        setLvl(lvl);
        setExperience(experience);
        setAnimals(animals);
        location = new Lobby();
        loadAvatarIcon();
        inventory = new Inventory(this);
        inventory.addAll(armors);
        inventory.addAll(weapons);
        gold = 0;
        lvl = 0;
//        inventory.addAll(foods);
    }

    public static boolean createPlayer(String name, String playerClass, int lvl, int experience, Stats stats,
                                       List<Skill> skills, List<Armor> armors, List<Weapon> weapons,
                                       List<Animal> animals) {
        if (player == null) {
            player = new Player(name, stats, playerClass, lvl, experience, skills, armors, weapons, animals);

            return true;
        }

        System.err.println("Player already exists!");
        return false;
    }

    public static Player getInstance() {
        if (player != null) {
            return player;
        }

        System.err.println("Player is not created yet!");
        return null;
    }

    public void newLvl() {
        lvl++;
    }

    @Override
    public String toString() {
        return
                "\nName='" + name + '\'' +
                        "\n" + stats.toString() +
                        "\nPlayerClass='" + playerClass +
                        "\nLvl=" + lvl +
                        "\nExperience =" + experience
                ;
    }

    public void loadAvatarIcon() {

        DBConnection db = DBConnection.getDbConnection();
        ResultSet resultSet = db.select("SELECT avatar FROM `Players`\n" +
                "WHERE player_name = 'tt';");
        try {
            if (resultSet.next()) {
                String avatar = resultSet.getString("avatar");
                avatarIcon = new Image("/assets/SIMPLEAvatarsIcons/512X512/" + avatar + ".png");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void heal(Item food) {

        switch (food.getName()) {
            case "begg food":
                player.getStats().heal(5);
                break;
            case "poor food":
                player.getStats().heal(10);
                break;
            case "modest food":
                player.getStats().heal(15);
                break;
            case "comfort food":
                player.getStats().heal(25);
                break;
            case "rich food":
                player.getStats().heal(50);
                break;
            case "aristocratic food":
                player.getStats().heal(100);
                break;
        }
        drop(food);
    }

    public void drop(Item item) {
        System.out.println("drop item = " + item.getName());
        inventory.getItems().remove(item);
    }

}
