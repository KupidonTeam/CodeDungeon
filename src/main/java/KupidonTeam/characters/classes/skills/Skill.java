package KupidonTeam.characters.classes.skills;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Skill {

    private int id;
    private String name;
    private int lvl;
    private String type;
    //    private int chance;
    private int countOfRandom;
    private int coolDown;
    private int randomDiapason;


    private String effect;

    public String toString() {
        return String.format("" +
                        "%s\n" +
                        "Level : %d\n" +
                        "Effect : %s\n" +
                        "Type : %s\n" +
                        "Chance : %d\n" +
                        "Cool Down : %d sec\n", name, lvl, effect, type,
                countOfRandom,
                coolDown);

    }

}
