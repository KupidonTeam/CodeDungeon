package KupidonTeam.characters;

import javafx.scene.control.Label;
import lombok.Getter;


@Getter

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
    private Label hpLabel;


    public Stats(int armorClass, int hits, int speed, int strength, int dexterity,
                 int intelligence, int wisdom, int chance, int constitution) {
        this.armorClass = armorClass;
        this.hits = hits;
        this.speed = speed;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.chance = chance;
        this.constitution = constitution;
        hpLabel = new Label("1111" + hits);
        hpLabel.setText("111111111111111111111");
    }


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

    public void setDamage(int damage) {
        hits -= damage;
        hpLabel.setText("" + hits + " %");
    }

    public void heal(int heal) {
        hits += heal;
        if (hits > 100) hits = 100;
    }

    public Integer[] getStatsData() {
        return new Integer[]{hits, speed, strength, dexterity, intelligence, wisdom, chance, constitution};
    }
}
