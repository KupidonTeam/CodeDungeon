package KupidonTeam.player;

import KupidonTeam.characters.classes.Stats;
import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.items.Item;
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
    private List<Skill> skills;
    private int lvl;
    private int experience;
    private Room location;


    private Player(String name, Stats stats, String playerClass, List<Skill> skills, int lvl, int experience) {
        setName(name);
        setStats(stats);
        setPlayerClass(playerClass);
        setSkills(skills);
        setLvl(lvl);
        setExperience(experience);
        location = new Lobby();
    }

    public static boolean createPlayer(String name, Stats stats, String playerClass, List<Skill> skills, int lvl, int experience) {
        if (player == null) {
            player = new Player(name, stats, playerClass, skills, lvl, experience);
            return true;
        }
        System.err.println("Player already exists!");
        return false;
    }

    public static Player getInstance() {
        if (player != null) return player;
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

}
