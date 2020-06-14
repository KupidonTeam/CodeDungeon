package KupidonTeam.characters.classes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Stats {

    private String race;
    private int armorClass;
    private int hits;
    private double speed;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int wisdom;
    private int chance;
    private int constitution;


    @Override
    public String toString() {
        return "Entity{" +
                "hits=" + hits +
                ", armorClass=" + armorClass +
                ", speed=" + speed +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", chance=" + chance +
                '}';
    }
}
