package KupidonTeam.player;

import KupidonTeam.animals.Animal;
import KupidonTeam.characters.classes.Stats;
import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.items.Armor;
import KupidonTeam.items.Food;
import KupidonTeam.items.Item;
import KupidonTeam.items.Weapon;
import KupidonTeam.locations.Lobby;
import KupidonTeam.locations.Room;

import lombok.Getter;
import lombok.Setter;

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
    private List<Food> foods;
    private List<Armor> armors;
    private List<Weapon> weapons;
    private List<Animal> animals;


    private Player(String name, Stats stats, String playerClass, int lvl, int experience, List<Skill> skills,
                   List<Armor> armors, List<Weapon> weapons, List<Animal> animals) {
        setName(name);
        setStats(stats);
        setPlayerClass(playerClass);
        setSkills(skills);
        setLvl(lvl);
        setExperience(experience);
        setFoods(foods);
        setArmors(armors);
        setWeapons(weapons);
        setAnimals(animals);
        location = new Lobby();
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


    public void playerDefeat() {
        // TODO
    }

    public void playerTurn() {
        // TODO
        //userInput();
    }

    public boolean isAlive() {
        return stats.getHits() > 0;
    }

    public void playerRewards(List<Item> rewards) {
        // TODO
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
}
