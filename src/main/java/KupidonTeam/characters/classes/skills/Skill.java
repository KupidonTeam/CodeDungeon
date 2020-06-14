package KupidonTeam.characters.classes.skills;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Skill {

    private int id;
    private String name;
    private int lvl;
    private AttackTypes type;
    private int chance;
    private int coolDown;
    private int randomRange;
    private String effect;

    public String skillInfo() {
        return String.format("" +
                "Name : %s\n" +
                "Level : %d\n" +
                "Effect : %s\n" +
                "Type : %s\n" +
                "Chance : %d\n" +
                "Cool Down : %d sec\n");

    }

}
