package KupidonTeam.characters.skills;

import KupidonTeam.utils.SoundPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Skill {
    private int id;
    private String name;
    private int lvl;
    private String type;
    private int countOfRandom;
    private int coolDown;
    private int randomDiapason;

    private String effect;

    public void skillSound() {
        new SoundPlayer().skill(effect);
    }

    public String toString() {
        return String.format("" +
                        "\"%d\" : {" +
                        "\"cooldown\" : %d\n," +
                        "\"count_of_random\" : %d\n," +
                        "\"effect\" : \"%s\"\n," +
                        "\"lvl\" : %d\n," +
                        "\"name\" : \"%s\", \n" +
                        "\"random_diapason\" : %d\n," +
                        "\"attack_type\" : \"%s\"}",
                id, coolDown, countOfRandom, effect, lvl, name, randomDiapason, type);
    }

}
