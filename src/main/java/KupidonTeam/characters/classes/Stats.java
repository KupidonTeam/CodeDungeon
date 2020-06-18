package KupidonTeam.characters.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Stats {

//    private String race;
    private int armorClass;
    private int hits;
    private int speed;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int wisdom;
    private int chance;
    private int constitution;


    @Override
    public String toString() {
        return String.format("" +
                        "\"armor_class\" : %d," +
                        "\"chance\" : %d," +
                        "\"constitution\" : %d," +
                        "\"dexterity\" : %d," +
                        "\"hits\" : %d," +
                        "\"intelligence\"  : %d," +
                        "\"speed\" : %d," +
                        "\"strength\" : %d," +
                        "\"wisdom\" : %d",
                armorClass, chance, constitution, dexterity, hits, intelligence, speed, strength, wisdom);
    }
}
